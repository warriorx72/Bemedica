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

import com.bemedica.springboot.app.models.dao.ICatalogoDao;
import com.bemedica.springboot.app.models.dao.IPerfilesCultivosDao;
import com.bemedica.springboot.app.models.dao.IPerfilesDao;
import com.bemedica.springboot.app.models.dao.IPerfilesEstudiosDao;
import com.bemedica.springboot.app.models.entity.Perfiles;
import com.bemedica.springboot.app.models.entity.PerfilesCultivos;
import com.bemedica.springboot.app.models.entity.PerfilesEstudios;

@Controller
public class PerfilesController {

	@Autowired
	private IPerfilesDao perfilesDao;
	@Autowired
	private ICatalogoDao catalogoDao;
	@Autowired
	private IPerfilesEstudiosDao perfilesEstudiosDao;
	@Autowired
	private IPerfilesCultivosDao perfilesCultivosDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("Titulo", "Listado de clientes");
		model.addAttribute("perfiles", perfilesDao.findAll());
		return "listar";
	}

	@RequestMapping(value = "/estudios_perfiles")
	public String crear(Map<String, Object> model, Model m) {
		// m.addAttribute("estudios", estudiosDao.findAll());
		m.addAttribute("catalogos", catalogoDao.findAll());
		Perfiles perfiles = new Perfiles();
		PerfilesEstudios perfilesEstudios = new PerfilesEstudios();
		PerfilesCultivos perfilesCultivos = new PerfilesCultivos();
		model.put("perfiles", perfiles);
		model.put("perfilesEstudios", perfilesEstudios);
		model.put("perfilesCultivos", perfilesCultivos);
		model.put("titulo", "Guardar Perfil");
		m.addAttribute("estudioss", perfilesEstudiosDao.findAllById(perfiles.getPerfilId()));
		return "estudios_perfiles";
	}

	@RequestMapping(value = "/estudios_perfiles/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, Model m) {

		Perfiles perfiles = null;
		PerfilesEstudios perfilesEstudios = new PerfilesEstudios();
		PerfilesCultivos perfilesCultivos = new PerfilesCultivos();
		if (id > 0) {
			System.out.print("i´m here" + id);
			perfiles = perfilesDao.findOne(id);
		} else {
			return "redirect:estudios_listar/";
		}
		model.put("perfiles", perfiles);
		perfilesEstudios.setPerfilId(id);
		perfilesCultivos.setPerfilId(id);
		model.put("perfilesEstudios", perfilesEstudios);
		model.put("perfilesCultivos", perfilesCultivos);
		model.put("titulo", "Guardar Perfil");
		m.addAttribute("catalogos", catalogoDao.findAll());
		m.addAttribute("estudios", perfilesEstudiosDao.findEstudios(id));
		m.addAttribute("cultivoss", perfilesCultivosDao.findAllById(id));
		m.addAttribute("estudioss", perfilesEstudiosDao.findAllById(id));
		return "estudios_perfiles";
	}

	@RequestMapping(value = "/estudios_perfiles", method = RequestMethod.POST)
	public String guardarPerfil(Map<String, Object> m, @Valid Perfiles perfiles, BindingResult result, Model model,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "estudios_perfiles";
		}
		if(perfiles.getPerfilEnvase()=="") {
			perfiles.setPerfilEnvase(null);
		}
		if(perfiles.getPerfilArea()=="") {
			perfiles.setPerfilArea(null);
		}
		perfiles.setPerfilEstatus(true);
		perfilesDao.save(perfiles);
		if(perfiles.getPerfilIdText()=="") {
			char buf[] = new char[3];
			perfiles.getPerfilNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			perfiles.setPerfilIdText(IdText.toLowerCase()+(perfiles.getPerfilId()+10000));
		}
		perfilesDao.save(perfiles);
		PerfilesCultivos perfilesCultivos = new PerfilesCultivos();
		PerfilesEstudios perfilesEstudios = new PerfilesEstudios();
		perfilesCultivos.setPerfilId(perfiles.getPerfilId());
		perfilesEstudios.setPerfilId(perfiles.getPerfilId());
		m.put("perfilesEstudios", perfilesEstudios);
		m.put("perfilesCultivos", perfilesCultivos);
		m.put("titulo", "Guardar Perfil");
		model.addAttribute("estudios", perfilesEstudiosDao.findEstudios(perfiles.getPerfilId()));
		model.addAttribute("estudioss", perfilesEstudiosDao.findAllById(perfiles.getPerfilId()));
		model.addAttribute("cultivoss", perfilesCultivosDao.findAllById(perfiles.getPerfilId()));
		model.addAttribute("catalogos", catalogoDao.findAll());
		return "estudios_perfiles";
	}

	@RequestMapping(value = "/estudios_perfilesEstudios", method = RequestMethod.POST)
	public String guardarPerfilEstudios(Map<String, Object> m, PerfilesEstudios perfilesEstudios, BindingResult result,
			Model model, SessionStatus status) {

		Perfiles aux = null;
		aux = perfilesDao.findOne(perfilesEstudios.getPerfilId());
		if (result.hasErrors()) {
			return "estudios_perfiles";
		}
		PerfilesCultivos perfilesCultivos = new PerfilesCultivos();
		perfilesEstudios.setPerfilId(aux.getPerfilId());
		perfilesCultivos.setPerfilId(aux.getPerfilId());
		perfilesEstudiosDao.save(perfilesEstudios);
		m.put("perfilesCultivos", perfilesCultivos);
		m.put("perfilesEstudios", perfilesEstudios);
		m.put("perfiles", aux);
		m.put("titulo", "Guardar Perfil");
		model.addAttribute("estudios", perfilesEstudiosDao.findEstudios(aux.getPerfilId()));
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("estudioss", perfilesEstudiosDao.findAllById(aux.getPerfilId()));
		model.addAttribute("cultivoss", perfilesCultivosDao.findAllById(aux.getPerfilId()));
		return "estudios_perfiles";
	}

	@RequestMapping(value = "/estudios_perfilesCultivos", method = RequestMethod.POST)
	public String guardarPerfilECultivos(Map<String, Object> m, PerfilesCultivos perfilesCultivos, BindingResult result,
			Model model, SessionStatus status) {

		Perfiles aux = null;
		aux = perfilesDao.findOne(perfilesCultivos.getPerfilId());
		if (result.hasErrors()) {
			return "estudios_perfiles";
		}
		PerfilesEstudios perfilesEstudios = new PerfilesEstudios();
		perfilesEstudios.setPerfilId(aux.getPerfilId());
		perfilesCultivos.setPerfilId(aux.getPerfilId());
		perfilesCultivosDao.save(perfilesCultivos);
		m.put("perfilesCultivos", perfilesCultivos);
		m.put("perfilesEstudios", perfilesEstudios);
		m.put("perfiles", aux);
		m.put("titulo", "Guardar Perfil");
		model.addAttribute("estudios", perfilesEstudiosDao.findEstudios(aux.getPerfilId()));
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("estudioss", perfilesEstudiosDao.findAllById(aux.getPerfilId()));
		model.addAttribute("cultivoss", perfilesCultivosDao.findAllById(aux.getPerfilId()));
		return "estudios_perfiles";
	}

//	@RequestMapping(value = "/eliminar2/{id}")
//	public String eliminar2(@PathVariable(value = "id") Long id) {
//
//		if (id > 0) {
//			perfilesDao.delete(id);
//		}
//		return "redirect:/clientes/listar";
//	}

	@RequestMapping(value = "/EliminarRecurso/{id}/{id_e}/{t}")
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "id_e") Long id_e,
			@PathVariable(value = "t") int t, Model m, Map<String, Object> model, Perfiles perfil) {
		Perfiles aux = null;
		aux = perfilesDao.findOne(id_e);
		PerfilesEstudios perfilesEstudios = new PerfilesEstudios();
		PerfilesCultivos perfilesCultivos = new PerfilesCultivos();
		perfilesCultivos.setPerfilId(aux.getPerfilId());
		perfilesEstudios.setPerfilId(aux.getPerfilId());
		model.put("perfilesEstudios", perfilesEstudios);
		model.put("perfilesCultivos", perfilesCultivos);
		model.put("perfiles", aux);
		model.put("titulo", "Guardar Perfil");
		if (t == 1 && id > 0) {

			perfilesEstudiosDao.delete(id);

		}

		else if (t == 3 && id > 0) {

			perfilesCultivosDao.delete(id);

		}
		m.addAttribute("estudioss", perfilesEstudiosDao.findAllById(id_e));
		m.addAttribute("estudios", perfilesEstudiosDao.findEstudios(id_e));
		m.addAttribute("catalogos", catalogoDao.findAll());
		m.addAttribute("estudioss", perfilesEstudiosDao.findAllById(id_e));
		m.addAttribute("cultivoss", perfilesCultivosDao.findAllById(id_e));
		return "estudios_perfiles";
	}
}
