package com.bemedica.springboot.app.controllers;

//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bemedica.springboot.app.models.dao.ICajaChicaDao;
import com.bemedica.springboot.app.models.dao.ICajaDao;
import com.bemedica.springboot.app.models.dao.ICajaVistaDao;
import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.entity.Caja;
import com.bemedica.springboot.app.models.entity.CajaChica;
import com.bemedica.springboot.app.models.entity.Direccion;
import com.bemedica.springboot.app.models.entity.Orden;
import com.bemedica.springboot.app.models.entity.Paciente;
import com.bemedica.springboot.app.models.entity.Persona;
import com.bemedica.springboot.app.service.UserService;

@Controller
@SessionAttributes("caja")
public class CajaController {
	@Autowired
	private ICajaDao cajaDao;

	@Autowired
	private ICajaVistaDao cajaVistaDao;

	@Autowired
	private ICajaChicaDao cajaChicaDao;
	
	@Autowired
	private UserService userService;
		
	
	@RequestMapping(value = "/herramientas_corte", method = RequestMethod.GET)

	public String listar(HttpServletRequest request,Model model, Map<String, Object> m) {
		UserController user = new UserController();
		Caja caja = new Caja();
		Orden orden = new Orden();
		CajaChica cach = new CajaChica();
		model.addAttribute("titulo", "Condiciones paciente");
		System.out.println("aaaaaaaaaa "+user.UserSucId(request, userService)[1]);
		model.addAttribute("titulo", "Corte de Caja");
		model.addAttribute("vista", cajaDao.findAll());
		model.addAttribute("vistas", cajaVistaDao.findAll(Integer.parseInt(user.UserSucId(request, userService)[1])));
		m.put("caja", caja);
		m.put("orden", orden);
		m.put("cach", cach);
		bloquear(model);
		cajaDao.findAll();
		return "herramientas_corte";
	}
	
	@RequestMapping(value = "/listar_cortes", method = RequestMethod.GET)
	public String listar2(Model model, Map<String, Object> m) {
		CajaChica cach= new CajaChica();
		Caja caja = new Caja();
		m.put("caja", caja);
		m.put("cach",cach);
		model.addAttribute("vistas", cajaVistaDao.findAll7());
		cach.setCajaId(cajaChicaDao.findAiCaja());
	
		return "listar_cortes";
	}
	
	public String lista(HttpServletRequest request, Model model, Map<String, Object> m) {
		UserController user = new UserController();
		Caja caja = new Caja();
		Orden orden = new Orden();
		CajaChica cach = new CajaChica();
		model.addAttribute("titulo", "Condiciones paciente");
		model.addAttribute("titulo", "Corte de Caja");
		model.addAttribute("vista", cajaDao.findAll());
		model.addAttribute("vistas", cajaVistaDao.findAll(Integer.parseInt(user.UserSucId(request, userService)[1])));
		m.put("caja", caja);
		m.put("orden", orden);
		m.put("cach", cach);
		bloquear(model);
		cajaDao.findAll();
		return "herramientas_corte";
	}
	
	@RequestMapping(value = "/corte", method = RequestMethod.POST)
	public String guardar(HttpServletRequest request, @Valid Caja caja, @Valid CajaChica cach, Model model, SessionStatus status,
			Map<String, Object> m) {
		UserController user = new UserController();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(formatter.format(date));
		model.addAttribute("vistas", cajaVistaDao.findAll(Integer.parseInt(user.UserSucId(request, userService)[1])));
		if (cajaDao.corteTipo() == true) {
			caja.setFechaInicial(formatter.format(date) + " " + "06:00:00");
		} else {
			caja.setFechaInicial(cajaDao.findLastCajaId());
		}
		cach.setCajaId(cajaChicaDao.findAiCaja());
		cajaChicaDao.save(cach);
		m.put("cach", cach);
		caja.setCorteTipo(false);
		cajaDao.save(caja);
		caja.setMontoEfectivo(cajaDao.findTotalEfectivo(caja.getCajaId()));
		caja.setMontoTarjeta(cajaDao.findTotalTarjeta(caja.getCajaId()));
		cajaDao.save(caja);
		cach.setCajaId(cajaChicaDao.findAiCaja());
		cajaChicaDao.save(cach);
		m.put("cach", cach);	
		m.put("caja", caja);
		return "redirect:/herramientas_corte";
	}
	@RequestMapping(value = "/cierre", method = RequestMethod.POST)
	public String guardar2(@Valid Caja caja, BindingResult result, Model model, SessionStatus status,
			Map<String, Object> me) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Caja cierre = new Caja();
		// Establecemos la fecha que deseamos en un Calendario
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		// Desplegamos la fecha
		Date tempDate = cal.getTime();
		System.out.println("Fecha actual:" + tempDate);

		// Le cambiamos la hora y minutos
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 5);
		tempDate = cal.getTime();
		System.out.println("Hora modificada:" + tempDate);

		String auxs = cajaDao.findLastCajaId();
		System.out.print("hola" + auxs);
		cierre.setMontoContado("0");
		cierre.setFechaInicial(formatter.format(date) + " " + "06:00:00");
		cierre.setCorteTipo(true);
		cajaDao.save(cierre);
		cierre.setMontoEfectivo(cajaDao.cierreCajaEfectivo(cierre.getCajaId()));
		cierre.setMontoTarjeta(cajaDao.cierreCajaTarjeta(cierre.getCajaId()));
		cajaDao.save(cierre);
		return "redirect:/herramientas_corte";
	}
	
	@RequestMapping(value = "/caja_chica", method = RequestMethod.POST)
	public String cajaChica(@Valid CajaChica cach, Model model, Map<String, Object> m) {
		cach.setCajaId(cajaChicaDao.findAiCaja());
		cajaChicaDao.save(cach);
		m.put("cach", cach);
		return "redirect:/herramientas_corte";
	}
	@RequestMapping(value = "/caja_chica2", method = RequestMethod.POST)
	public String cajaChica2(@Valid CajaChica cach, Model model, Map<String, Object> m) {
		cach.setCajaId(cajaChicaDao.findAiCaja());
		cajaChicaDao.save(cach);
		m.put("cach", cach);
		return "redirect:/listar_cortes";
	}
	
	
	private void bloquear(Model model) {
		if(cajaDao.bloqueoCorte()) {
			model.addAttribute("bloqueo","disabled");
		}
		else {
			model.addAttribute("bloqueo","false");
			
		}
	}
}