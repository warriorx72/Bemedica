package com.bemedica.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import com.bemedica.springboot.app.models.dao.IDireccion;
import com.bemedica.springboot.app.models.dao.IEmpleadosSucursal;
import com.bemedica.springboot.app.models.dao.IPersona;
import com.bemedica.springboot.app.models.dao.ISucursal;
import java.util.Map;
import javax.validation.Valid;
import com.bemedica.springboot.app.models.entity.EmpleadosSucursal;
import com.bemedica.springboot.app.models.entity.Persona;
import com.bemedica.springboot.app.models.entity.PersonaE;
import com.bemedica.springboot.app.models.entity.Direccion;
import com.bemedica.springboot.app.models.entity.DireccionE;

@Controller
public class EmpleadosSucursalController{
	
	@Autowired
	private IPersona PersonaDao;
	@Autowired
	private IDireccion DireccionDao;
	@Autowired
	private IEmpleadosSucursal EmpleadosSucursalDao;
	
	
	@Autowired
	private ISucursal sucursalDao;
	
	
	@RequestMapping (value="/administracion_empleados", method=RequestMethod.GET)
	public String listar (Model model) {
		model.addAttribute("titulo","Empleados");
		model.addAttribute("vista", EmpleadosSucursalDao.All());
		return "administracion_empleados";
	}
	
	@RequestMapping (value="/administracion_empleados_filtro", method=RequestMethod.POST)
	public String listar_filtro (Model model, @RequestParam("filtro") String filtro) {
		
		if (filtro.equals("10")) {
			
			return"redirect:administracion_empleados";
		}
		
		
		if (filtro.equals("1")) {
			
			model.addAttribute("titulo","Empleados activos");
			model.addAttribute("vista", EmpleadosSucursalDao.filtrado(filtro));
			
			
			
			return "administracion_empleados";
		}
		
		if (filtro.equals("0")) {
			
			model.addAttribute("titulo","Empleados inactivos");
			model.addAttribute("vista", EmpleadosSucursalDao.filtrado(filtro));
			
			
			
			return "administracion_empleados";
		}
		return"redirect:administracion_empleados";
	}
	
	
	@RequestMapping(value="/form_empleado")
	public String crear (Map<String, Object> model , Model m)
	{
		m.addAttribute("vistas", sucursalDao.findAll());
		DireccionE d = new DireccionE();
		PersonaE p = new PersonaE();
		EmpleadosSucursal e = new EmpleadosSucursal();
		model.put("direccion", d);
		model.put("persona", p);
		model.put("empleadosSucursal", e);

		model.put("titulo","Nuevo Empleado");
		
	
		
		return "form_empleado";
	
	}
	
	@RequestMapping(value= "/form_empleado", method=RequestMethod.POST)
	public String guardar (@Valid DireccionE direccion,BindingResult resultd ,@Valid PersonaE persona, BindingResult resultp ,@Valid EmpleadosSucursal empleado,BindingResult resulte , Model model , RedirectAttributes redirectAttrs){
		
		
		/*
		if ( resultd.hasErrors() || resultp.hasErrors() || resulte.hasErrors()){
			model.addAttribute("titulo","Editar Empleado");
			model.addAttribute("vistas", sucursalDao.findAll());
			
			return "form_empleado";
		}*/
		
	
			DireccionDao.save(direccion);
			
			persona.setIdDireccion(direccion.getDireccionId().intValue());
			
			persona.setPersonaAp(mayuscula(persona.getPersonaAp()));
			
			persona.setPersonaAm(mayuscula(persona.getPersonaAm()));
			
			persona.setPersonaNombre(mayuscula(persona.getPersonaNombre()));
			PersonaDao.save(persona);
			
			
			empleado.setPersonaId(persona.getPersonaId().intValue());
		
			
			
			EmpleadosSucursalDao.save(empleado);
			
			empleado.setEmpleadoIdText("EMP"+persona.getPersonaAp().charAt(0)+persona.getPersonaAm().charAt(0)+persona.getPersonaNombre().charAt(0)+""+(empleado.getEmpleadoId()+1000));
			
			EmpleadosSucursalDao.save(empleado);
			redirectAttrs
            .addFlashAttribute("mensaje", "Empleado guardado correctamente")
            .addFlashAttribute("clase", "success");
			
			
			
			return"redirect:administracion_empleados";
			
		
		
		
	}
	
	@RequestMapping(value= "/form_empleado_editar/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model, Model m ) {
		
		DireccionE d = null;
		PersonaE p = null;
		EmpleadosSucursal e = null;
		
		
		
		if (id>0) {
			
			e=EmpleadosSucursalDao.findOne(id.longValue());
			
			p=PersonaDao.findOne(e.getPersonaId().longValue());
			
			d=DireccionDao.findOne(p.getIdDireccion().longValue());
			
			m.addAttribute("vistas", sucursalDao.findAll());
			model.put("titulo", "Editar Empleado");
			model.put("empleadosSucursal", e);
			model.put("persona", p);
			model.put("direccion", d);
			
			return "form_empleado";
			
		}
		
		else {
			return "redirect:/administracion_empleados";
		}
		
		
		
		
	}
	
	@RequestMapping (value="/eliminar_emp/{id}")
	public String eliminar(@PathVariable (value="id") Long id  , RedirectAttributes redirectAttrs) {
		if (id > 0 )
		{
			DireccionE d = null;
			PersonaE p = null;
			EmpleadosSucursal e = null;
			e=EmpleadosSucursalDao.findOne(id.longValue());
			p=PersonaDao.findOne(e.getPersonaId().longValue());
			Long idpersona = p.getPersonaId();
			d=DireccionDao.findOne(p.getIdDireccion().longValue());
			Long iddi =d.getDireccionId();
			EmpleadosSucursalDao.delete(id);
			PersonaDao.delete(idpersona);
			DireccionDao.delete(iddi);
			redirectAttrs
            .addFlashAttribute("mensaje", "Empleado eliminado correctamente")
            .addFlashAttribute("clase", "success");
		}
		
		return "redirect:/administracion_empleados";
	}
	
	
	@RequestMapping (value="/estatus_empleado", method = RequestMethod.POST)
	public String estatus (@RequestParam("id") Long id,@RequestParam("aux") int aux, Model model, RedirectAttributes redirectAttrs){
		EmpleadosSucursal e ;
		e=EmpleadosSucursalDao.findOne(id.longValue());
		
		if (aux==1){
			
		    e.setEmpleadoEstatus("0");
		    EmpleadosSucursalDao.save(e);
		    
		    redirectAttrs
            .addFlashAttribute("mensaje", "Empleado desactivado ")
            .addFlashAttribute("clase", "success");
		}
		else{
			e.setEmpleadoEstatus("1");
			EmpleadosSucursalDao.save(e);
			
			redirectAttrs
            .addFlashAttribute("mensaje", "Empleado activado")
            .addFlashAttribute("clase", "success");

		}
		
		return "redirect:/administracion_empleados";
	
	}
	
	
	public String mayuscula(String str) {
		  if (str == null || str.isEmpty()) {
		    return "";            
		  } else {
		    return  Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length()).toLowerCase();
		  }
		}
}
