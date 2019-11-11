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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bemedica.springboot.app.models.dao.ICatalogoDao;
import com.bemedica.springboot.app.models.entity.Catalogo;


@Controller
@SessionAttributes("catalogo")
public class CatalogoController {
		
		@Autowired
		private ICatalogoDao catalogoDao;
		
	/********************************************************************************/
		@RequestMapping(value="/catalogos_condicion", method=RequestMethod.GET)
		
		public String listar(Model model, Map<String, Object> m) {
			
			Catalogo catalogo = new Catalogo();
			model.addAttribute("titulo","Condiciones paciente");
			
			model.addAttribute("titulo1","Formulario catalogo");
			model.addAttribute("vista", catalogoDao.findAll());
			m.put("catalogo", catalogo);
			
			return "catalogos_condicion";
		}
		
		@RequestMapping(value="/catalogos_condicion")
		public String crear(Map<String, Object> model) {
			Catalogo catalogo = new Catalogo();
			
			model.put("catalogo", catalogo);
			model.put("titulo", "Formulario de catalogos");
			return "catalogos_condicion";
		}
		

		@RequestMapping(value="/catalogos_condicion", method=RequestMethod.POST)
		public String guardar(@Valid Catalogo catalogo ,BindingResult result, Model model, SessionStatus status) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de catalogos");
				return "catalogos_condicion";
			}
			
			catalogoDao.save(catalogo);
			status.setComplete();
			return "redirect:/catalogos_condicion";
			
		}
		
		@RequestMapping(value="/catalogos_condicion/{CatalogoId}")
		public String editar(@PathVariable(value= "CatalogoId") Long CatalogoId, Map<String, Object> model) {
			
			Catalogo catalogo = null;
			if(CatalogoId > 0) {
				catalogo = catalogoDao.	findOne(CatalogoId);  
				
			}else {
				return "redirect:/catalogos_condicion";
			}
		
		    model.put("catalogo", catalogo);
		    model.put("titulo", "Editar cliente");
			return "/catalogos_condicion";
		}
		
		
		
		@RequestMapping(value="/eliminar/{CatalogoId}")
		public String eliminar(@PathVariable(value= "CatalogoId") Long CatalogoId) {
			
			if(CatalogoId > 0) {
				catalogoDao.delete(CatalogoId);
			}
			return "redirect:/catalogos_condicion";
		} 
		
	/********************************************************************************/
		@RequestMapping(value="/catalogos_tecnicas", method=RequestMethod.GET)
		public String listar1(Model model, Map<String, Object> m) {
			Catalogo catalogo = new Catalogo();
			model.addAttribute("titulo","Listado de Catalogos");
			model.addAttribute("titulo2","Formulario catalogo");
			model.addAttribute("catalogos", catalogoDao.findAll());
			m.put("catalogo", catalogo);
			return "catalogos_tecnicas";
		}//end listar1
		
		@RequestMapping(value="/catalogos_tecnicas")
		public String crear1(Map<String, Object> model) {
			Catalogo catalogo = new Catalogo();
			model.put("catalogo", catalogo);
			model.put("titulo", "Formulario de catalogos");
			return "catalogos_tecnicas";
		}
		

		@RequestMapping(value="/catalogos_tecnicas", method=RequestMethod.POST)
		public String guardar1(@Valid Catalogo catalogo ,BindingResult result, Model model, SessionStatus status) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de catalogos");
				return "catalogos_tecnicas";
			}
			
			catalogoDao.save(catalogo);
			status.setComplete();
			return "redirect:/catalogos_tecnicas";
			
		}
		
		@RequestMapping(value="/eliminar1/{CatalogoId}")
		public String eliminar1(@PathVariable(value= "CatalogoId") Long CatalogoId) {
			
			if(CatalogoId > 0) {
				catalogoDao.delete(CatalogoId);
			}
			return "redirect:/catalogos_tecnicas";
		}//end eliminar1
		
	/********************************************************************************/
		
		@RequestMapping(value="/catalogos_unidades", method=RequestMethod.GET)
		public String listar2(Model model, Map<String, Object> m) {
			Catalogo catalogo = new Catalogo();
			model.addAttribute("titulo","Listado de Catalogos");
			model.addAttribute("titulo3","Formulario catalogo");
			model.addAttribute("catalogos", catalogoDao.findAll());
			m.put("catalogo", catalogo);
			return "catalogos_unidades";
		}//end listar2
		
		@RequestMapping(value="/catalogos_unidades")
		public String crear2(Map<String, Object> model) {
			Catalogo catalogo = new Catalogo();
			model.put("catalogo", catalogo);
			model.put("titulo", "Formulario de catalogos");
			return "catalogos_unidades";
		}
		

		@RequestMapping(value="/catalogos_unidades", method=RequestMethod.POST)
		public String guardar2(@Valid Catalogo catalogo ,BindingResult result, Model model, SessionStatus status) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de catalogos");
				return "catalogos_unidades";
			}
			
			catalogoDao.save(catalogo);
			status.setComplete();
			return "redirect:/catalogos_unidades";
			
		}
		
		
		@RequestMapping(value="/eliminar2/{CatalogoId}")
		public String eliminar2(@PathVariable(value= "CatalogoId") Long CatalogoId) {
			
			if(CatalogoId > 0) {
				catalogoDao.delete(CatalogoId);
			}
			return "redirect:/catalogos_unidades";
		}//end eliminar2
		

		
		
	}



