package com.bemedica.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedica.springboot.app.models.dao.IMedicoPagoDao;
import com.bemedica.springboot.app.models.dao.IMedicosDao;
import com.bemedica.springboot.app.models.entity.MedicoPago;
import com.bemedica.springboot.app.models.entity.Medicos;
import com.bemedica.springboot.app.models.entity.Persona;

@Controller
public class MedicoPagoController {

	@Autowired
	private IMedicoPagoDao MedicoPagoDao;
	
	@Autowired
	private IMedicosDao medicosDao;
	
	@Autowired
	private IMedicoPagoDao medicopagoDao;
	
	
	
	@RequestMapping(value="/registro_pago_medico")
	public String crear(Map<String, Object> model, Model m) {
		
		m.addAttribute("vista",medicosDao.mv());
		MedicoPago mp= new MedicoPago();
		Medicos me = new Medicos();
		Persona p = new Persona();
		model.put("medicopago", mp);
		model.put("medicos",me);
		model.put("persona", p);
		model.put("titulo", "Registro de Pago");
		
		return "registro_pago_medico";	
	}
	
	@RequestMapping(value="/registro_pago_medico", method=RequestMethod.POST)
	public String guardar(@Valid MedicoPago medicoPago,BindingResult resultmp) {
		medicopagoDao.save(medicoPago);
		return "redirect:herramientas_participaciones";	
	}
	
	@RequestMapping(value="/registro_pago_medico/{Id}")
	public String editar(@PathVariable(value="Id") Long Id, Map<String, Object> model, Model m) {
		
		MedicoPago mp = null;	
		
		if(Id > 0) {
			System.out.print("Hola"+Id);
			mp = medicopagoDao.findOne(Id);
			
			
		}else {
			return "redirect:herramientas_participaciones";
		}
		m.addAttribute("vista",medicosDao.mv());
		model.put("titulo", "Editar Registro");
		model.put("medicopago", mp);		
		
		return "registro_pago_medico";
	}
	
	
	@RequestMapping(value="/eliminar/{MPVId}")
	public String eliminar(@PathVariable(value="MPVId") Long MPVId) {
		
		if(MPVId > 0) {
			MedicoPagoDao.delete(MPVId);
		}
		return "redirect:/herramientas_participaciones";
	}
}
