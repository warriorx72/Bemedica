package com.bemedica.springboot.app.controllers;

//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.bemedica.springboot.app.models.dao.ICajaDao;
import com.bemedica.springboot.app.models.dao.ICajaVistaDao;
import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.entity.Caja;
import com.bemedica.springboot.app.models.entity.Orden;


@Controller
@SessionAttributes("caja")
public class CajaController {
	
	@Autowired
	private ICajaDao cajaDao;
	
	@Autowired
	private ICajaVistaDao cajaVistaDao;
	
	@Autowired
	private IOrdenDao OrdenDao;
	
	
	@RequestMapping(value="/herramientas_corte", method=RequestMethod.GET)
	
	public String listar(Model model, Map<String, Object> m) {
		
		Caja caja = new Caja();
		model.addAttribute("titulo","Condiciones paciente");
		
		model.addAttribute("titulo","Corte de Caja");
		model.addAttribute("vista", cajaDao.findAll());
		model.addAttribute("vistas", cajaVistaDao.findAll());
		m.put("caja", caja);
		cajaDao.findAll();
		return "herramientas_corte";
	   }
	
		@RequestMapping(value="/corte", method=RequestMethod.POST)
		public String guardar(@Valid Caja caja ,BindingResult result, Model model, SessionStatus status,  Map<String, Object> m,
				@RequestParam(value = "EnCaja", defaultValue="0") String EnCaja) {
			
			Orden orden =new Orden ();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   	
		    Date date = new Date();  
		    System.out.println(formatter.format(date)); 
		   
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "herramientas_corte";
		}
		

		orden.setOrden_id(null);
		orden.setOrden_folio("EnCaja");
		orden.setPaciente_id(null);
		orden.setSucursal_id(null);
		orden.setEmpleado_id(null);
		orden.setPago_inicial("0.00");
		orden.setOrden_estatus("Corte Caja");
		orden.setOrden_fecha(formatter.format(date)+" "+"06:00:00");
		orden.setMonto(EnCaja);
		orden.setPago_final(EnCaja);
		orden.setFecha_liquidacion(formatter.format(date)+" "+"06:00:00");
		
		OrdenDao.save(orden);
		
		caja.setFechaInicial(formatter.format(date) +" "+"06:00:00");
		cajaDao.save(caja);
		Caja CajaAux ;
		
		CajaAux=cajaDao.findOne(caja.getCajaId());
		
		String aux = cajaDao.caja(caja.getCajaId());
		
		
		
		orden.setOrden_fecha(aux);
		orden.setFecha_liquidacion(aux);
		
		 OrdenDao.save(orden);
		//status.setComplete();
		m.put("caja", caja);
		return "redirect:/herramientas_corte";
        }
		
		
		
		@RequestMapping(value="/cierre", method=RequestMethod.POST)
		public String guardar2(@Valid Caja caja ,BindingResult result, Model model, SessionStatus status, Map<String, Object> me) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = new Date();  
			Orden orden =new Orden ();
			
			//Establecemos la fecha que deseamos en un Calendario
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			
			//Desplegamos la fecha
			Date tempDate = cal.getTime();
			System.out.println("Fecha actual:" + tempDate);
			
			//Le cambiamos la hora y minutos
			cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)+ 5);
			tempDate = cal.getTime();
			System.out.println("Hora modificada:" + tempDate);
				
			
			String auxs =cajaDao.findLastCajaId();
			System.out.print("hola"+auxs);
			
			
			caja.setFechaInicial(auxs);
			
			cajaDao.save(caja);
			
		
			
		    Date date1 = new Date();  
		    System.out.println(formatter.format(date1)); 
		    
		    
		   
		
		return "redirect:/herramientas_corte";
        }	
	
}