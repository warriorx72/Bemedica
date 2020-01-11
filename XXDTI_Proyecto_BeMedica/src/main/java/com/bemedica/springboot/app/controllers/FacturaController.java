package com.bemedica.springboot.app.controllers;

/*import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;*/
/*import java.util.Map;
ADAN
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bemedica.springboot.app.models.dao.IFacturaDao;
import com.bemedica.springboot.app.models.entity.Factura;*/


//@SessionAttributes("factura")
public class FacturaController {

	/*@Autowired
	private IFacturaDao facturaDao;
	@Autowired
	@Qualifier("DireccionDaoJPA")
	private IDireccionDao direccionDao;
	
	
	@RequestMapping(value="/operaciones_facturacion",method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Facturas");
		model.addAttribute("facturas",facturaDao.findAll());
		return "operaciones_facturacion";
	}
	
	@RequestMapping(value="/registro_informacion_fiscal")
	public String crear(Map<String, Object> model) {
		
		Factura factura = new Factura();
		model.put("factura", factura);
		model.put("titulo", "Formulario de Factura");
	return "registro_informacion_fiscal";
	}
	
	
	@RequestMapping(value="/registro_informacion_fiscal/{facturaId}")
	public String editar(@PathVariable(value="facturaId")Long facturaId, Map<String,Object> model) {
		
		Factura factura = null;
		
		if(facturaId > 0) {
			factura = facturaDao.findOne(facturaId);
		}
		else {
			return "redirect:/operaciones_facturacion";
		}
		model.put("factura", factura);
		model.put("titulo", "Editar datos");
		return "registro_informacion_fiscal";
	}
	
	
	@RequestMapping(value="/registro_informacion_fiscal", method=RequestMethod.POST)
	public String guardar(@Valid Factura factura, BindingResult result, SessionStatus status) {
		
		if(result.hasErrors()) {
			return "registro_informacion_fiscal";
		}
		facturaDao.save(factura);
		status.setComplete();
		
		
		 
		return "redirect:/operaciones_facturacion";
	}
	
	
	@RequestMapping(value="/eliminar/{facturaId}")
	public String eliminar(@PathVariable(value="facturaId") Long facturaId) {
		
		if(facturaId > 0) {
			facturaDao.delete(facturaId);
		}
		return "redirect:/operaciones_facturacion";
	}*/
}
