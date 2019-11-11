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
import com.bemedica.springboot.app.models.dao.IAntibioticoDao;
import com.bemedica.springboot.app.models.entity.Antibiotico;
//import com.bemedica.springboot.app.models.entity.Catalogo;

@Controller
public class AntibioticoController {
	
	@Autowired
	private IAntibioticoDao antibioticoDao;
	
	/********************************************************************************/
	@RequestMapping(value="/catalogos_antibiotico", method=RequestMethod.GET)
	
	public String listar(Model model, Map<String, Object> m) {
		
		Antibiotico antibiotico = new Antibiotico();
		model.addAttribute("titulo","Catalogo Antibioticos");
		
		model.addAttribute("titulo1","Formulario antibiticos");
		model.addAttribute("anti", antibioticoDao.findAll());
		m.put("antibiotico", antibiotico);
		
		return "catalogos_antibiotico";
	}//end_listar
	
	@RequestMapping(value="/catalogos_antibiotico")
	public String crea(Map<String, Object> model) {
		Antibiotico antibiotico = new Antibiotico();
		
		model.put("antibiotico", antibiotico);
		model.put("titulo", "Formulario de antibioticos");
		return "catalogos_antibiotico";
	}//end_crear

	@RequestMapping(value="/catalogos_antibiotico", method=RequestMethod.POST)
	public String guarda(@Valid Antibiotico antibiotico ,BindingResult result, Model model, SessionStatus status) {
		
	if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de antibioticos");
			return "catalogos_antibiotico";
		}
	    antibiotico.setNombreCat("antibiotico");
		antibioticoDao.save(antibiotico);
		status.setComplete();
		return "redirect:/catalogos_antibiotico";
	}//end_guardar
	
	@RequestMapping(value="/elimina/{CatalogoId}")
	public String elimina(@PathVariable(value= "CatalogoId") Long CatalogoId) {
		
		if(CatalogoId > 0) {
			antibioticoDao.delete(CatalogoId);
		}
		return "redirect:/catalogos_antibiotico";
	}//end_eliminar
	
	
	

}
