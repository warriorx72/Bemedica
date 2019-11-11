package com.bemedica.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedica.springboot.app.models.dao.IDatosFacturaDao;
import com.bemedica.springboot.app.models.dao.IFacturaDao;
import com.bemedica.springboot.app.models.dao.IFacturaVistaDao;
import com.bemedica.springboot.app.models.dao.IPacientesDao;
import com.bemedica.springboot.app.models.entity.DatosFactura;
import com.bemedica.springboot.app.models.entity.Factura;
import com.bemedica.springboot.app.models.entity.PacienteVista;
import com.bemedica.springboot.app.models.entity.Pacientes;
import com.bemedica.springboot.app.models.entity.Persona;

@Controller
public class FacturaVistaController {

	@Autowired
	private IFacturaVistaDao facturavistaDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IPacientesDao pacientesDao;
	
	@Autowired
	private IDatosFacturaDao datosfacturaDao;
	
	@RequestMapping(value="/operaciones_facturacion",method=RequestMethod.GET)
	public String listar( Map<String, Object> model, Model m) {
		m.addAttribute("titulo","Listado de Facturas");
		m.addAttribute("factura_vista",facturavistaDao.findAll());
		m.addAttribute("paciente_vista",pacientesDao.pv());
		Factura factura = new Factura();
		Pacientes pa = new Pacientes();
		Persona p = new Persona();
		PacienteVista pv = new PacienteVista();
		DatosFactura df = new DatosFactura();
		model.put("factura", factura);
		model.put("pacientes", pa);
		model.put("persona", p);
		model.put("pacientevista", pv);
		model.put("datosfactura", df);
		model.put("titulo", "Facturas");
		return "operaciones_facturacion";
	}
	
	/*@RequestMapping(value="/pacientes",method=RequestMethod.GET)
	public String listar2(Model model) {
		model.addAttribute("titulo","Listado de Pacientes");
		model.addAttribute("paciente_vista",pacientesDao.pv());
		return "pacientes";
	}*/
	
	/*@RequestMapping(value="/operaciones_facturacion")
	public String crear(Map<String, Object> model, Model m) {
		
		m.addAttribute("paciente_vista",pacientesDao.pv());
		Factura factura = new Factura();
		Pacientes pa = new Pacientes();
		Persona p = new Persona();
		PacienteVista pv = new PacienteVista();
		DatosFactura df = new DatosFactura();
		model.put("factura", factura);
		model.put("pacientes", pa);
		model.put("persona", p);
		model.put("pacientevista", pv);
		model.put("datosfactura", df);
		model.put("titulo", "Facturas");
	return "registro_informacion_fiscal";
	}*/
	
	@RequestMapping(value="/operaciones_facturacion/{Id}")
	public String editar(@PathVariable(value="Id") Long Id, Map<String, Object> model, Model m) {
		
		DatosFactura dfn = new DatosFactura();
		DatosFactura df = null;
		
		if(Id > 0) {
			System.out.print("Hola"+Id);
			df = datosfacturaDao.findOne(Id);
			System.out.print("Hola"+df);
			
		}else {
			return "redirect:/operaciones_facturacion";
		}
		
		m.addAttribute("paciente_vista",pacientesDao.pv());
		model.put("titulo", "Editar o Agregar");
		model.put("datosfactura", df);
		model.put("datosfactura", dfn);
		
		return "operaciones_facturacion";
	}
	
	
	@RequestMapping(value="/operaciones_facturacion", method = RequestMethod.POST)
	public String editar(DatosFactura df,  Map<String, Object> model, Model m) {
		
		Long aux = df.getPacienteId();		
		DatosFactura dfn ;
		dfn=datosfacturaDao.findOne(aux);
		m.addAttribute("paciente_vista",pacientesDao.pv());
		model.put("titulo", "Editar o Agregar");
		
		model.put("datosfactura", dfn);
		
		return "operaciones_facturacion";
	}
	
	@RequestMapping(value="/operaciones_facturacion_guardar", method=RequestMethod.POST)
	public String save(@Valid DatosFactura datosfactura,BindingResult resuldf, Model model) {
		//		model.put("datosfactura", datosfactura);
		
		datosfacturaDao.save(datosfactura);
		
		return "redirect:operaciones_facturacion";	
	}
	
	
	
	@RequestMapping(value="/eliminar/{facturaId}")
	public String eliminar(@PathVariable(value="facturaId") Long facturaId) {
		
		if(facturaId > 0) {
			facturaDao.delete(facturaId);
		}
		return "redirect:/operaciones_facturacion";
	}
}
