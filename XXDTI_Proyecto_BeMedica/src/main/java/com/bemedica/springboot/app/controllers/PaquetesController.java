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
import org.springframework.web.bind.support.SessionStatus;

import com.bemedica.springboot.app.models.dao.IPaquetesCultivosDao;
import com.bemedica.springboot.app.models.dao.IPaquetesDao;
import com.bemedica.springboot.app.models.dao.IPaquetesEstudiosDao;
import com.bemedica.springboot.app.models.dao.IPaquetesGabinetesDao;
import com.bemedica.springboot.app.models.dao.IPaquetesPerfilesDao;
import com.bemedica.springboot.app.models.dao.IPerfilesDao;
import com.bemedica.springboot.app.models.entity.Paquetes;
import com.bemedica.springboot.app.models.entity.PaquetesCultivos;
import com.bemedica.springboot.app.models.entity.PaquetesEstudios;
import com.bemedica.springboot.app.models.entity.PaquetesGabinetes;
import com.bemedica.springboot.app.models.entity.PaquetesPerfiles;

@Controller
public class PaquetesController {
	
	@Autowired
	private IPaquetesDao paquetesDao;
	
	@Autowired
	private IPaquetesEstudiosDao paquetesEstudiosDao;
	
	@Autowired
	private IPaquetesPerfilesDao paquetesPerfilesDao;
	
	@Autowired
	private IPaquetesCultivosDao paquetesCultivosDao;
	
	@Autowired
	private IPaquetesGabinetesDao paquetesGabinetesDao;
	
	@Autowired
	private IPerfilesDao perfilesDao;
	
	
	@RequestMapping(value="/estudios_paquetes")
	public String crear (Map<String, Object> model,Model m)
	{
		
		Paquetes paquetes = new Paquetes();
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		PaquetesGabinetes paquetesGabinetes = new PaquetesGabinetes();
		model.put("paquetes",paquetes);
		model.put("paquetesEstudios", paquetesEstudios);
		model.put("paquetesPerfiles", paquetesPerfiles);
		model.put("paquetesCultivos", paquetesCultivos);
		model.put("paquetesGabinetes", paquetesGabinetes);
		model.put("titulo","Guardar Paquete");	
		//m.addAttribute("valoresListar", valorReferenciaDao.findAllById(estudios.getEstudioId()));
		return "estudios_paquetes";
	
	}
	
	@RequestMapping(value="/estudios_paquetes", method=RequestMethod.POST)
	public String guardarPaquete (Map<String, Object> m,@Valid Paquetes paquetes,BindingResult result , Model model,SessionStatus status)
	{		
		//model.addAttribute("catalogos", catalogoDao.findAll());
		
		m.put("titulo","Guardar Paquete");
		
		model.addAttribute("perfiles", perfilesDao.findAll());
		//model.addAttribute("estudioss", perfilesEstudiosDao.findAllById(perfiles.getPerfilId()));
		if(result.hasErrors()) {
			return "estudios_listar";
		}
		paquetes.setPaqueteEstatus(true);
		paquetes.setBeMedicaId(1);
		paquetesDao.save(paquetes);
		if(paquetes.getPaqueteIdText()=="") {
			char buf[] = new char[3];
			paquetes.getPaqueteNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			paquetes.setPaqueteIdText(IdText.toLowerCase()+(paquetes.getPaqueteId()+10000));
		}
		paquetesDao.save(paquetes);
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		paquetesEstudios.setPaqueteId(paquetes.getPaqueteId());
		paquetesPerfiles.setPaqueteId(paquetes.getPaqueteId());
		paquetesCultivos.setPaqueteId(paquetes.getPaqueteId());
		m.put("paquetesEstudios",paquetesEstudios);
		m.put("paquetesPerfiles", paquetesPerfiles);
		m.put("paquetesCultivos", paquetesCultivos);
		model.addAttribute("estudios", paquetesEstudiosDao.findEstudios(paquetes.getPaqueteId()));
		model.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(paquetes.getPaqueteId()));
		model.addAttribute("estudioss", paquetesEstudiosDao.findAllById(paquetes.getPaqueteId()));
		model.addAttribute("perfiless", paquetesPerfilesDao.findAllById(paquetes.getPaqueteId()));
		model.addAttribute("cultivoss", paquetesCultivosDao.findAllById(paquetes.getPaqueteId()));
		return "estudios_paquetes";
	}
	
	@RequestMapping(value="/estudios_paquetesEstudios", method=RequestMethod.POST)
	public String guardarPaqueteEstudios (Map<String, Object> m,PaquetesEstudios paquetesEstudios,BindingResult result , Model model,SessionStatus status)
	{	
		//model.addAttribute("catalogos", catalogoDao.findAll());
		Paquetes aux=null;
		aux=paquetesDao.findOne(paquetesEstudios.getPaqueteId());
		if(result.hasErrors()) {
			return "estudios_perfiles";
		}
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		paquetesCultivos.setPaqueteId(aux.getPaqueteId());
		paquetesEstudios.setPaqueteId(aux.getPaqueteId());
		paquetesPerfiles.setPaqueteId(aux.getPaqueteId());
		paquetesEstudiosDao.save(paquetesEstudios);
		m.put("titulo","Guardar Paquete");	
		m.put("paquetesEstudios",paquetesEstudios);
		m.put("paquetesPerfiles", paquetesPerfiles);	
		m.put("paquetesCultivos", paquetesCultivos);
		m.put("paquetes",aux);
		model.addAttribute("estudios", paquetesEstudiosDao.findEstudios(aux.getPaqueteId()));
		model.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(aux.getPaqueteId()));
		model.addAttribute("estudioss", paquetesEstudiosDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("perfiless", paquetesPerfilesDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("cultivoss", paquetesCultivosDao.findAllById(aux.getPaqueteId()));
		return "estudios_paquetes";
	}
	@RequestMapping(value="/estudios_paquetesPerfiles", method=RequestMethod.POST)
	public String guardarPaquetePerfiles (Map<String, Object> m,PaquetesPerfiles paquetesPerfiles,BindingResult result , Model model,SessionStatus status)
	{	
		//model.addAttribute("catalogos", catalogoDao.findAll());
		Paquetes aux=null;
		aux=paquetesDao.findOne(paquetesPerfiles.getPaqueteId());
		if(result.hasErrors()) {
			return "estudios_perfiles";
		}
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		paquetesCultivos.setPaqueteId(aux.getPaqueteId());
		paquetesPerfiles.setPaqueteId(aux.getPaqueteId());
		paquetesEstudios.setPaqueteId(aux.getPaqueteId());
		paquetesPerfilesDao.save(paquetesPerfiles);
		m.put("titulo","Guardar Paquete");	
		m.put("paquetesPerfiles",paquetesPerfiles);
		m.put("paquetesEstudios",paquetesEstudios);
		m.put("paquetesCultivos", paquetesCultivos);
		m.put("paquetes",aux);
		model.addAttribute("estudios", paquetesEstudiosDao.findEstudios(aux.getPaqueteId()));
		model.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(aux.getPaqueteId()));
		model.addAttribute("estudioss", paquetesEstudiosDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("perfiless", paquetesPerfilesDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("cultivoss", paquetesCultivosDao.findAllById(aux.getPaqueteId()));

		return "estudios_paquetes";
	}
	@RequestMapping(value="/estudios_paquetesCultivos", method=RequestMethod.POST)
	public String guardarPaquetesCultivos (Map<String, Object> m,PaquetesCultivos paquetesCultivos,BindingResult result , Model model,SessionStatus status)
	{	
		//model.addAttribute("catalogos", catalogoDao.findAll());
		Paquetes aux=null;
		aux=paquetesDao.findOne(paquetesCultivos.getPaqueteId());
		if(result.hasErrors()) {
			return "estudios_perfiles";
		}
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		paquetesCultivos.setPaqueteId(aux.getPaqueteId());
		paquetesPerfiles.setPaqueteId(aux.getPaqueteId());
		paquetesEstudios.setPaqueteId(aux.getPaqueteId());
		paquetesCultivosDao.save(paquetesCultivos);
		m.put("titulo","Guardar Paquete");	
		m.put("paquetesCultivos", paquetesCultivos);
		m.put("paquetesPerfiles",paquetesPerfiles);
		m.put("paquetesEstudios",paquetesEstudios);
		m.put("paquetes",aux);
		model.addAttribute("estudios", paquetesEstudiosDao.findEstudios(aux.getPaqueteId()));
		model.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(aux.getPaqueteId()));
		model.addAttribute("estudioss", paquetesEstudiosDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("perfiless", paquetesPerfilesDao.findAllById(aux.getPaqueteId()));
		model.addAttribute("cultivoss", paquetesCultivosDao.findAllById(aux.getPaqueteId()));

		return "estudios_paquetes";
	}
	
	@RequestMapping(value="/estudios_paquetes/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) {
		Paquetes paquetes = null;
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		PaquetesGabinetes paquetesGabinetes = new PaquetesGabinetes();
		if(id>0) {
			System.out.print("i´m here"+id);
			paquetes=paquetesDao.findOne(id);
		}
		else {
			return "redirect:estudios_listar/";
		}
		model.put("paquetes",paquetes);
		paquetesEstudios.setPaqueteId(id);
		paquetesCultivos.setPaqueteId(id);
		paquetesPerfiles.setPaqueteId(id);
		paquetesEstudios.setPaqueteId(id);
		model.put("paquetesCultivos", paquetesCultivos);
		model.put("paquetesPerfiles",paquetesPerfiles);
		model.put("paquetesEstudios",paquetesEstudios);
		model.put("paquetesGabinetes",paquetesGabinetes);
		model.put("titulo","Guardar Paquete");	
		m.addAttribute("estudioss", paquetesEstudiosDao.findAllById(id));
		m.addAttribute("cultivoss", paquetesCultivosDao.findAllById(id));
		m.addAttribute("estudios", paquetesEstudiosDao.findEstudios(id));
		m.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(id));
		m.addAttribute("estudioss", paquetesEstudiosDao.findAllById(id));
		m.addAttribute("perfiless", paquetesPerfilesDao.findAllById(id));
		m.addAttribute("cultivoss", paquetesCultivosDao.findAllById(id));
		return "estudios_paquetes";		
	}
	@RequestMapping (value="/EliminarEstPaq/{id}/{id_e}/{t}")
	public String eliminar(@PathVariable (value="id") Long id,@PathVariable (value="id_e") Long id_e,@PathVariable (value="t") int t,Model m,Map<String, Object> model,Paquetes paquetes) {
		//m.addAttribute("catalogos", catalogoDao.findAll());
		model.put("titulo","Guardar Paquete");
		Paquetes aux=null;
		aux=paquetesDao.findOne(id_e);
		PaquetesPerfiles paquetesPerfiles = new PaquetesPerfiles();
		PaquetesEstudios paquetesEstudios = new PaquetesEstudios();
		PaquetesCultivos paquetesCultivos = new PaquetesCultivos();
		paquetesCultivos.setPaqueteId(aux.getPaqueteId());
		paquetesPerfiles.setPaqueteId(aux.getPaqueteId());
		paquetesEstudios.setPaqueteId(aux.getPaqueteId());
		model.put("paquetesCultivos", paquetesCultivos);
		model.put("paquetesPerfiles",paquetesPerfiles);
		model.put("paquetesEstudios",paquetesEstudios);
		model.put("paquetes",aux);
		if(t==1 && id > 0) {

			paquetesEstudiosDao.delete(id);

		}
		else if(t==2 && id > 0) {
			
			paquetesPerfilesDao.delete(id);
			
		}
		else if(t==3 && id > 0) {
			
			paquetesCultivosDao.delete(id);
			
		}
		m.addAttribute("estudios", paquetesEstudiosDao.findEstudios(id_e));
		m.addAttribute("perfiles", paquetesPerfilesDao.findPerfiles(id_e));
		m.addAttribute("perfiless", paquetesPerfilesDao.findAllById(id_e));
		m.addAttribute("estudioss", paquetesEstudiosDao.findAllById(id_e));
		m.addAttribute("cultivoss", paquetesCultivosDao.findAllById(id_e));
		return "estudios_paquetes";
	}

}
