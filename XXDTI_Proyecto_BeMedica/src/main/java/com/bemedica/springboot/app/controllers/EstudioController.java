package com.bemedica.springboot.app.controllers;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bemedica.springboot.app.models.dao.IEstudio;
import com.bemedica.springboot.app.models.entity.Estudio;
import com.bemedica.springboot.app.models.entity.EstudioE;

@Controller
public class EstudioController {
	
	@Autowired
	@Qualifier("EstudioDaoJPA")
	private IEstudio EstudioDao;
	
	
	@RequestMapping (value="/precios_listas", method=RequestMethod.GET)
	public String listar (Model model , Map<String, Object> mo) {
		
		EstudioE estudio = new EstudioE();
		model.addAttribute("titulo","Lista de Estudios");
		model.addAttribute("vista", EstudioDao.findAll());
		mo.put("estudio", estudio);
		return "precios_listas";
	}

	@RequestMapping(value= "/form_estudio_precio/{id}", method=RequestMethod.POST)
	public String guardar ( EstudioE estudio,@PathVariable (value="id") Long id, Model model , Map<String, Object> mo  ){
		EstudioE e = null;
		if(id>0 && estudio.getEstudioPrecio()!=null)
		{
			e=EstudioDao.findOne(id);
			e.setEstudioPrecio(estudio.getEstudioPrecio());
			EstudioDao.save(e);
			EstudioE aux = new EstudioE();
			model.addAttribute("titulo","Lista de Estudios");
			model.addAttribute("vista", EstudioDao.findAll());
			mo.put("estudio", aux);
			
			model.addAttribute("mensaje", "Precio actualizado correctamente")
	        .addAttribute("clase", "success");
			return "precios_listas";
		}
		EstudioE aux = new EstudioE();
		model.addAttribute("titulo","Lista de Estudios");
		model.addAttribute("vista", EstudioDao.findAll());
		mo.put("estudio", aux);
		model.addAttribute("mensaje", "¡Error! no se actualizo el precio")
        .addAttribute("clase", "danger");

		
		return "precios_listas";
	}
	
	
	@RequestMapping(value= "/form_estudio_precio_porcentaje/{id}", method=RequestMethod.POST)
	public String guarda ( EstudioE estudio,@PathVariable (value="id") Long id,  Model model , Map<String, Object> mo    ){
		EstudioE e = null;
		if(id>0 && estudio.getEstudioPrecio()!=null)
		{
			e=EstudioDao.findOne(id);
			
			e.setEstudioPrecio(((estudio.getEstudioPrecio()/100)*e.getEstudioPrecio())+e.getEstudioPrecio());
			EstudioDao.save(e);
			
			EstudioE aux = new EstudioE();
			model.addAttribute("titulo","Lista de Estudios");
			model.addAttribute("vista", EstudioDao.findAll());
			mo.put("estudio", aux);
			model.addAttribute("mensaje", "Precio actualizado correctamente")
	        .addAttribute("clase", "success");
			return "precios_listas";
		}
		
		EstudioE aux = new EstudioE();
		model.addAttribute("titulo","Lista de Estudios");
		model.addAttribute("vista", EstudioDao.findAll());
		mo.put("estudio", aux);
		model.addAttribute("mensaje", "¡Error! no se actualizo el precio")
        .addAttribute("clase", "danger");
		return "precios_listas";
	}
	

}
