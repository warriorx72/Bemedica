package com.bemedica.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bemedica.springboot.app.models.dao.IDireccion;
import com.bemedica.springboot.app.models.dao.ISucursal;
import com.bemedica.springboot.app.models.entity.DireccionE;
import com.bemedica.springboot.app.models.entity.SucursalE;

@Controller
public class SucursalController {
	
	@Autowired
	private IDireccion DireccionDao;
	@Autowired
	private ISucursal sucursalDao;
	
	
	@RequestMapping (value="/administracion_sucursales", method=RequestMethod.GET)
	public String listar (Model model) {
		model.addAttribute("titulo","Sucursal");
		model.addAttribute("vista",sucursalDao.listarSucursales());
		return "listar_sucursales";
	}
	
	@RequestMapping(value="/form_sucursal")
	public String crear (Map<String, Object> model , Model m)
	{
	
		DireccionE d = new DireccionE();
		SucursalE su = new SucursalE();
		model.put("direccion", d);
		model.put("sucursal", su);
		model.put("titulo","Nueva sucursal");
		
	
		
		return "form_sucursal";
	
	}
	
	@RequestMapping(value= "/form_sucursal", method=RequestMethod.POST)
	public String guardar (@Valid DireccionE direccion,@Valid SucursalE sucursal, Model model , RedirectAttributes redirectAttrs){
			DireccionDao.save(direccion);
			sucursal.setDireccionId(direccion.getDireccionId());
			sucursal.setEstatus(true);
			sucursalDao.save(sucursal);
			
			sucursal.setSucusalIdText("SUC"+(sucursal.getSucursalId()+1000));
			sucursalDao.save(sucursal);
			redirectAttrs
            .addFlashAttribute("mensaje", "Sucursal guardada correctamente")
            .addFlashAttribute("clase", "success");
			return"redirect:/administracion_sucursales";
	}
	
	@RequestMapping (value="/eliminar_sucur/{id}")
	public String eliminar(@PathVariable (value="id") Long id  , RedirectAttributes redirectAttrs) {
		
		SucursalE sucursal = sucursalDao.findOne(id);
		
		sucursal.setEstatus(false);
		sucursalDao.save(sucursal);
	
		return"redirect:/administracion_sucursales";
	
	
	}
	//hola
	@RequestMapping(value= "/form_sucursal_editar/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model, Model m ) {
		DireccionE d;
		SucursalE su;
		if (id>0) {
			
			su=sucursalDao.findOne(id);
			d=DireccionDao.findOne(su.getDireccionId());
			
			model.put("titulo", "Editar sucursal");
			model.put("direccion", d);
			model.put("sucursal", su);
			
			return "form_sucursal";
			
		}
		
		else {
			return "redirect:/administracion_sucursales";
		}
		
		
		
		
	}


}
