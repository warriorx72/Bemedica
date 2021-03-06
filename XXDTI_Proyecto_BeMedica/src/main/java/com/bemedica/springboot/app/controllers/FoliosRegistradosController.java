package com.bemedica.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.dao.IOrdenVistaDao;
import com.bemedica.springboot.app.models.dao.IResultados;
import com.bemedica.springboot.app.models.dao.ITicketDao;
import com.bemedica.springboot.app.models.dao.IVistaOrdenDao;
import com.bemedica.springboot.app.models.entity.Resultados;
import com.bemedica.springboot.app.service.UserService;
import com.bemedica.springboot.app.models.entity.Orden;
@Controller
public class FoliosRegistradosController {
	
	@Autowired
	@Qualifier("ResultadosDaoJPA")
	private IResultados ResultadosDao;
	
	@Autowired
	private IVistaOrdenDao OrdenVista;
	
	@Autowired
	private IOrdenVistaDao vistaOrden;
	@Autowired
	private IVistaOrdenDao vistaordenDao;
	
	@Autowired
	private IOrdenDao OrdenDao;
	
	@Autowired
	private ITicketDao ticketDao;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping (value="/operaciones_folios", method=RequestMethod.GET)
	public String listar (HttpServletRequest request,Model model,Map<String, Object> m) {
	////	-----------------------------------------------------
		UserController us =new UserController();
		us.UserSucId(request,userService);
		String id_empleado=us.UserSucId(request, userService)[0];
		String id_sucursal=us.UserSucId(request, userService)[1];
		System.out.println("de folios"+id_sucursal);
		model.addAttribute("titulo","Folios registrados");
		model.addAttribute("vista", OrdenVista.findAll2(id_sucursal));
	////	------------------------------------------
		model.addAttribute("servicios", vistaOrden.findById());
		model.addAttribute("message",ticketDao.findServAll());
		return "operaciones_folios";
	}
	
	@RequestMapping (value="/detalles/{id}")
	public String listar_detalles (@PathVariable (value="id") Long id, Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "detalles_orden";
	}
	
	@SuppressWarnings("null")
	@RequestMapping (value="/ticket/{id}")
	public String ImpTicket (@PathVariable (value="id") Long id, Model model) 
	{
		
		Orden orden ;
		orden=OrdenDao.findOne(id);
		
		if(orden.getConvenio_id() != null)
		{
			model.addAttribute("eminfo", ticketDao.findEmpleado(id));
			model.addAttribute("painfo", ticketDao.findPaciente(id));
			model.addAttribute("feinfo", ticketDao.findFecha(id));
			model.addAttribute("seinfo", ticketDao.findServ(id));
			model.addAttribute("toinfo", ticketDao.findTotal(id));
			model.addAttribute("coninfo", ticketDao.findConvenio(id));
			model.addAttribute("coninfo", ticketDao.findConvenio(id));
			model.addAttribute("bloque", "block9");
			model.addAttribute("vista_ordenes", vistaordenDao.findAll(id));
		} 
		else
		{
			model.addAttribute("eminfo", ticketDao.findEmpleado(id));
			model.addAttribute("painfo", ticketDao.findPaciente(id));
			model.addAttribute("feinfo", ticketDao.findFecha(id));
			model.addAttribute("seinfo", ticketDao.findServ(id));
			model.addAttribute("toinfo", ticketDao.findTotal(id));
			model.addAttribute("bloque", "block5");
			model.addAttribute("vista_ordenes", vistaordenDao.findAll(id));
		}
		
		
		return "ticket";
	}
	
	
	@RequestMapping (value="/liquidar_orden/{id}",method = RequestMethod.GET)
	public String liquidarOrden (Model model, @PathVariable (value="id") Long id,HttpServletRequest request) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		Orden orden ;
		orden=OrdenDao.findOne(id);
		if (Double.parseDouble(orden.getPago_inicial()) == 0) {
			orden.setPago_final(orden.getMonto());
			orden.setFecha_liquidacion(dtf.format(now));
			//orden.setOrden_estatus("Pagado");
		}
		else {
			orden.setPago_final(String.valueOf( Double.parseDouble(orden.getMonto())-Double.parseDouble(orden.getPago_inicial())));
			orden.setFecha_liquidacion(dtf.format(now));		
			//orden.setOrden_estatus("Pagado");
		}
		
		OrdenDao.save(orden);
		model.addAttribute("titulo","Folios registrados");
		UserController us =new UserController();
		us.UserSucId(request,userService);
		String id_empleado=us.UserSucId(request, userService)[0];
		String id_sucursal=us.UserSucId(request, userService)[1];
		model.addAttribute("vista", OrdenVista.findAll2(id_sucursal));
		return "redirect:/operaciones_folios";
	}
	
	@RequestMapping (value="/cancelar_orden/{id}",method = RequestMethod.GET)
	public String cancelarOrden (Model model, @PathVariable (value="id") Long id,HttpServletRequest request) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   
		Orden orden ;
		orden=OrdenDao.findOne(id);
		orden.setOrden_estatus("Cancelada");
		orden.setFecha_cancelacion(dtf.format(now));
		OrdenDao.save(orden);
		model.addAttribute("titulo","Folios registrados");
		UserController us =new UserController();
		us.UserSucId(request,userService);
		String id_empleado=us.UserSucId(request, userService)[0];
		String id_sucursal=us.UserSucId(request, userService)[1];
		model.addAttribute("vista", OrdenVista.findAll2(id_sucursal));
		return "redirect:/operaciones_folios";
	}
	
	@RequestMapping (value="/finalizar_orden/{id}",method = RequestMethod.GET)
	public String finalizarOrden (Model model, @PathVariable (value="id") Long id,HttpServletRequest request) {
		Orden orden ;
		orden=OrdenDao.findOne(id);
		
		if (orden.getOrden_estatus().equals("A entrega") && orden.getAdeudo().equals("0.00")) {
			orden.setOrden_estatus("Finalizada");
		}
		
		
		OrdenDao.save(orden);
		model.addAttribute("titulo","Folios registrados");
		UserController us =new UserController();
		us.UserSucId(request,userService);
		String id_empleado=us.UserSucId(request, userService)[0];
		String id_sucursal=us.UserSucId(request, userService)[1];
		model.addAttribute("vista", OrdenVista.findAll2(id_sucursal));
		return "redirect:/operaciones_folios";
	}
	
	
	
	
	@RequestMapping (value="/detalles_detalles/{id}/{i}/{t}/{lo}", method=RequestMethod.GET)
	public String agregar_resultado (@PathVariable (value="id") Long id, 
			@PathVariable (value="i") Long i,
			@PathVariable (value="t") String tipo,
			@PathVariable (value="lo") Long lo,
			Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		Resultados auxr = new Resultados ();
		m.addAttribute("aux", auxr);
		if ( ResultadosDao.findAll(lo) == null ||  ResultadosDao.findAll(lo).isEmpty() ){
			if (tipo.equals("estudio")){
				List <Object[]> aux  = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados ();
				 for (Object[] a : aux) {
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }//ggg
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti("0.00");
					 }
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 resul.setPerfil(a[1].toString());
					 ResultadosDao.save(resul);
				 }
			}
			if (tipo.equals("perfil")){
				List <Object[]> aux  = ResultadosDao.Perfil(i);
				String nombre = ResultadosDao.NombrePerfil(i);
				for (Object[] a : aux) {
					Resultados resul = new Resultados ();
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti("0.00");
					 }
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 resul.setPerfil(nombre);
					 ResultadosDao.save(resul);
					}
			}
			if (tipo.equals("paquete")){
				List <Object[]> aux  = ResultadosDao.Paquete(i);
				for (Object[] a : aux) {
					Resultados resul = new Resultados ();
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti("0.00");
					 }
					 resul.setComentario("");
					 resul.setValidacion("0");
					 if (a[3].toString()=="null" || a[3].toString().equals("null") ){
						 resul.setPerfil(a[1].toString());
					 }else {
						 resul.setPerfil(a[3].toString());
					 }						 
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 ResultadosDao.save(resul);
					}	
			}
		}
		m.addAttribute("resul",  ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "detalles_orden";
	}

}
