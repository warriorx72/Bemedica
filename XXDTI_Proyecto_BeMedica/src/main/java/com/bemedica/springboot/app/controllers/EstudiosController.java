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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bemedica.springboot.app.models.dao.IAlertasDao;
import com.bemedica.springboot.app.models.dao.IAntibiogramasDao;
import com.bemedica.springboot.app.models.dao.ICatalogoDao;
import com.bemedica.springboot.app.models.dao.IEstudiosDao;
import com.bemedica.springboot.app.models.dao.IPaquetesDao;
import com.bemedica.springboot.app.models.dao.IPerfilesDao;
import com.bemedica.springboot.app.models.dao.IValorReferenciaDao;
import com.bemedica.springboot.app.models.entity.Antibiogramas;
import com.bemedica.springboot.app.models.entity.Estudios;
import com.bemedica.springboot.app.models.entity.Paquetes;
import com.bemedica.springboot.app.models.entity.Perfiles;
import com.bemedica.springboot.app.models.entity.ValorReferencia;



@Controller
public class EstudiosController {
	
	@Autowired
	private IEstudiosDao estudiosDao;
	
	@Autowired
	private IValorReferenciaDao valorReferenciaDao;
	
	@Autowired
	private IPerfilesDao perfilesDao;
	
	@Autowired
	private IPaquetesDao paquetesDao;
	
	@Autowired
	private IAntibiogramasDao antibiogramasDao;
	
	@Autowired
	private IAlertasDao alertasDao;
	
	
	@RequestMapping (value="/estudios_listar", method=RequestMethod.GET)
	public String listar (Model model) {
		model.addAttribute("titulo","Listar Estudios");
		model.addAttribute("estudios", estudiosDao.findAllEstudios());
		Estudios estudios = new Estudios();
		model.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		return "estudios_listar";
	}
	
	@Autowired
	private ICatalogoDao catalogoDao;
	
	@RequestMapping(value="/estudios_examenes")
	public String crear (Map<String, Object> model,Model m)
	{
		m.addAttribute("catalogos", catalogoDao.findAll());
		
		Estudios estudios = new Estudios();
		ValorReferencia valores = new ValorReferencia();
		model.put("estudios",estudios);
		model.put("valores", valores);
		model.put("titulo","Guardar Estudio");	
		m.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		m.addAttribute("alertas", alertasDao.findAll());
		return "estudios_examenes";
	
	}
	
	@RequestMapping(value="/estudios_cultivos")
	public String crear2 (Map<String, Object> model,Model m)
	{
		m.addAttribute("catalogos", catalogoDao.findAll());
		
		Estudios estudios = new Estudios();
		ValorReferencia valores = new ValorReferencia();
		model.put("estudios",estudios);
		model.put("valores", valores);
		model.put("titulo","Guardar Cultivo");	
		m.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		return "estudios_cultivos";
	
	}
	

	@RequestMapping(value= "/estudios_examenes", method=RequestMethod.POST)
	public String guardarEstudio (Map<String, Object> m,@Valid Estudios estudios,BindingResult result , Model model,SessionStatus status)
	{		
		
		model.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		if(result.hasErrors()) {
			return "estudios_examenes";
		}
		m.put("titulo","Guardar Estudio");
		estudios.setBeMedicaId("1");
		estudios.setEstudioTipo("1");
		estudios.setEstudioEstatus(true);
		estudiosDao.save(estudios);
		if(estudios.getEstudioIdText()=="") {
			char buf[] = new char[3];
			estudios.getEstudioNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			estudios.setEstudioIdText(IdText.toLowerCase()+(estudios.getEstudioId()+10000));
		}
		estudiosDao.save(estudios);
		ValorReferencia valores = new ValorReferencia();
		valores.setEstudioId(estudios.getEstudioId());
		m.put("valores",valores);
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("alertas", alertasDao.findAll());
		return "estudios_examenes";
	}
	
	@RequestMapping(value= "/estudios_cultivos", method=RequestMethod.POST)
	public String guardarCultivo (Map<String, Object> m,@Valid Estudios estudios,BindingResult result , Model model,SessionStatus status)
	{		
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		m.put("titulo","Guardar Cultivo");
		if(result.hasErrors()) {
			return "estudios_examenes";
		}
		estudios.setBeMedicaId("1");
		estudios.setEstudioTipo("2");
		estudios.setEstudioEstatus(true);
		estudiosDao.save(estudios);
		if(estudios.getEstudioIdText()=="") {
			char buf[] = new char[3];
			estudios.getEstudioNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			estudios.setEstudioIdText(IdText.toLowerCase()+(estudios.getEstudioId()+10000));
		}
		estudiosDao.save(estudios);
		ValorReferencia valores = new ValorReferencia();
		valores.setEstudioId(estudios.getEstudioId());
		m.put("valores",valores);
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("alertas", alertasDao.findAll());
		return "redirect:/estudios_listar";
	}
	
	@RequestMapping(value= "/estudios_valores", method=RequestMethod.POST)
	public String guardarValor (Map<String, Object> m,Estudios estudio,ValorReferencia valores,BindingResult result , Model model,SessionStatus status)
	{		
		Estudios estudios = new Estudios();
		model.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		Estudios aux=null;
		m.put("titulo","Guardar Estudio");
		aux=estudiosDao.findOne(valores.getEstudioId());
		if(result.hasErrors()) {
			return "estudios_examenes";
		}
		if(valores.getP2()=="") {
			valores.setP2(null);
		}
		if(valores.getP3()=="") {
			valores.setP3(null);
		}
		if(valores.getP4()=="") {
			valores.setP4(null);
		}
		if(valores.getP5()=="") {
			valores.setP5(null);
		}
		if(valores.getP6()=="") {
			valores.setP6(null);
		}
		if(valores.getP7()=="") {
			valores.setP7(null);
		}
		valorReferenciaDao.save(valores);
		m.put("valores", valores);
		m.put("estudios",aux);
		model.addAttribute("alertas", alertasDao.findAll());
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("valoresListar", valorReferenciaDao.findAllById(aux.getEstudioId()));
		return "estudios_examenes";
	}
	
	@RequestMapping(value= "/estudios_examenes/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) {
		
		
		Estudios estudios = null;
		ValorReferencia valores = new ValorReferencia();
		if(id>0) {
			estudios=estudiosDao.findOne(id);
		}
		else {
			return "redirect:estudios_listar/";
		}
		model.put("estudios",estudios);
		valores.setEstudioId(id);
		model.put("valores",valores);
		model.put("titulo","Guardar Estudio");
		m.addAttribute("alertas", alertasDao.findAll());
		m.addAttribute("catalogos", catalogoDao.findAll());
		m.addAttribute("valoresListar", valorReferenciaDao.findAllById(id));
		return "estudios_examenes";		
	}
	
	@RequestMapping(value= "/estudios_cultivos/{id}")
	public String editar2(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) {
		m.addAttribute("catalogos", catalogoDao.findAll());
		
		Estudios estudios = null;
		if(id>0) {
			estudios=estudiosDao.findOne(id);
		}
		else {
			return "redirect:estudios_listar/";
		}
		model.put("estudios",estudios);
		model.put("titulo","Guardar Cultivo");
		return "estudios_cultivos";		
	}
	
	@RequestMapping (value="/eliminar_estudio/{id}")
	public String eliminar(@PathVariable (value="id") Long id) {
		
		
		if(id > 0) {
			estudiosDao.delete(id);
		}

		return "redirect:/estudios_listar";
	}
	@RequestMapping (value="/eliminar_valor/{id}/{id_e}")
	public String eliminarValor(@PathVariable (value="id") Long id,@PathVariable (value="id_e") Long id_e,Model m,Estudios estudio,Map<String, Object> model) {

		
		if(id > 0) {
			
			model.put("titulo","Guardar Estudio");
			Estudios aux=null;
			aux=estudiosDao.findOne(id_e);
			ValorReferencia valores = new ValorReferencia();
			valores.setEstudioId(aux.getEstudioId());
			model.put("valores", valores);
			model.put("estudios",aux);
			valorReferenciaDao.delete(id);
			m.addAttribute("valoresListar", valorReferenciaDao.findAllById(aux.getEstudioId()));
			m.addAttribute("catalogos", catalogoDao.findAll());
			m.addAttribute("alertas", alertasDao.findAll());
			
		}

		return "estudios_examenes";
	}
	
	@RequestMapping (value="/estatus_estudios", method = RequestMethod.POST)
	public String estatus (@RequestParam("id") Long id,@RequestParam("aux") boolean aux,@RequestParam("cat") String cat, Model model, RedirectAttributes redirectAttrs){
		Estudios e ;
		Perfiles pe;
		Paquetes pa;
		Antibiogramas a;
		e=estudiosDao.findOne(id);
		pe=perfilesDao.findOne(id);
		pa=paquetesDao.findOne(id);
		a=antibiogramasDao.findOne(id);
		if(cat.equals("Examenes")||cat.equals("Cultivos")){
			if (aux==true){
				
			    e.setEstudioEstatus(false);
			    estudiosDao.save(e);
			    
			    redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado desactivado ")
	            .addFlashAttribute("clase", "success");
			}
			else{
				e.setEstudioEstatus(true);
				estudiosDao.save(e);
				
				redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado activado")
	            .addFlashAttribute("clase", "success");
	
			}
		}
		else if(cat.equals("Perfiles")){
			if (aux==true){
				
				pe.setPerfilEstatus(false);
				perfilesDao.save(pe);
			    
			    redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado desactivado ")
	            .addFlashAttribute("clase", "success");
			}
			else{
				pe.setPerfilEstatus(true);
				perfilesDao.save(pe);
				
				redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado activado")
	            .addFlashAttribute("clase", "success");
	
			}
			
			
		}
		else if(cat.equals("Paquetes")){
			if (aux==true){
				
				pa.setPaqueteEstatus(false);
				paquetesDao.save(pa);
			    
			    redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado desactivado ")
	            .addFlashAttribute("clase", "success");
			}
			else{
				pa.setPaqueteEstatus(true);
				paquetesDao.save(pa);
				
				redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado activado")
	            .addFlashAttribute("clase", "success");
	
			}
			
			
		}
		
		else if(cat.equals("Antibiogramas")){
			if (aux==true){
				
				a.setAntiEstatus(false);
				antibiogramasDao.save(a);
			    
			    redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado desactivado ")
	            .addFlashAttribute("clase", "success");
			}
			else{
				a.setAntiEstatus(true);
				antibiogramasDao.save(a);
				
				redirectAttrs
	            .addFlashAttribute("mensaje", "Empleado activado")
	            .addFlashAttribute("clase", "success");
	
			}
			
			
		}
		
		return "redirect:/estudios_listar";
	
	}
}

