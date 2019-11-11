package com.bemedica.springboot.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.dao.IOrdenVistaDao;
@Controller
public class OrdenVistaController {
	@Autowired
	private IOrdenVistaDao ordenVistaDao;
	
	@Autowired
	private IOrdenDao ordenDao;
	
	@RequestMapping(value="/operaciones_folios",method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("ordenes",ordenVistaDao.findAll());		
		return "operaciones_folios";		
	}
	
	@RequestMapping (value="/eliminar_orden/{id}")
	public String eliminar(@PathVariable (value="id") Long id) {
		
		if (id > 0)
		{		
			ordenDao.delete(id);
		}
		
		return "redirect:/operaciones_folios";
	}
	
}
