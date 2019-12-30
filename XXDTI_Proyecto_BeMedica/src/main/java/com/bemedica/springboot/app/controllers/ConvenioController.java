package com.bemedica.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bemedica.springboot.app.models.dao.IConvenio;
import com.bemedica.springboot.app.models.dao.IConvenioEstudio;
import com.bemedica.springboot.app.models.dao.IConvenioPaqueteDao;
import com.bemedica.springboot.app.models.dao.IConvenioPerfilDao;
import com.bemedica.springboot.app.models.dao.IEmpresa;
import com.bemedica.springboot.app.models.dao.IEstudio;
import com.bemedica.springboot.app.models.entity.Convenio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudio;
import com.bemedica.springboot.app.models.entity.ConvenioPaquete;
import com.bemedica.springboot.app.models.entity.ConvenioPerfil;

@Controller
public class ConvenioController {
	
	@Autowired
	@Qualifier("ConvenioDaoJPA")
	private IConvenio ConvenioDao;
	
	@Autowired
	@Qualifier("EmpresaDaoJPA")
	private IEmpresa EmpresaDao;
	
	@Autowired
	@Qualifier("ConvenioEstudioDaoJPA")
	private IConvenioEstudio ConvenioEstudioDao;
	
	@Autowired
	@Qualifier("EstudioDaoJPA")
	private IEstudio EstudioDao;
	
	@Autowired
	@Qualifier("ConvenioPerfilDaoJPA")
	private IConvenioPerfilDao CpeDao;

	@Autowired
	@Qualifier("ConvenioPaqueteDaoJPA")
	private IConvenioPaqueteDao CopaDao;
	
	
	
	@RequestMapping (value="/precios_convenios", method=RequestMethod.GET)
	public String listar (Model m) {
		mostrar(null,m);
		return "precios_convenios";
	}
	
	@RequestMapping(value="/form_convenio")
	public String crear (Map<String, Object> model , Model m)
	{
		Convenio c = new Convenio();
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPerfil cope =new ConvenioPerfil();
		ConvenioPaquete copa =new ConvenioPaquete();
		model.put("convenio", c);
		model.put("ce", ce);
		model.put("cope", cope);
		model.put("copa", copa);
		model.put("titulo","Nuevo Convenio");
		m.addAttribute("vistas", EmpresaDao.findAll());
		return "form_convenio";
	
	}
	
	public void mostrar(Long id, Model m) {
		m.addAttribute("titulo","Convenios");
		m.addAttribute("vista", ConvenioDao.All());
		m.addAttribute("vistas", EmpresaDao.findAll());
		m.addAttribute("vistasEstudio", EstudioDao.select(id));
		m.addAttribute("vistasPaquete", CopaDao.findPaquete(id));
		m.addAttribute("vistasPerfil", CpeDao.findPerfil(id));	
		m.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(id));
		m.addAttribute("vista_convenio_perfil", CpeDao.findPerfilConvenio(id));
		m.addAttribute("vista_convenio_paquete", CopaDao.findPaqueteConvenio(id));
		
	}
	
	
	@RequestMapping(value= "/form_convenio_convenio", method=RequestMethod.POST)
	public String guardar_convenio (@Valid Convenio convenio,BindingResult result, Model model, Map<String, Object> m ){		
		convenio.setConvenioIdText("null");
		ConvenioDao.save(convenio);
		convenio.setConvenioIdText("CONV"+convenio.getConvenioNombre().charAt(0)+convenio.getConvenioNombre().charAt(1)+convenio.getConvenioNombre().charAt(2)+""+(convenio.getConvenioId()+10000));
		ConvenioDao.save(convenio);
		model.addAttribute("mensaje", "Convenio guardado correctamente").addAttribute("clase", "success");
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPerfil cope =new ConvenioPerfil();
		ConvenioPaquete copa =new ConvenioPaquete();
		ce.setConvenioId(convenio.getConvenioId());
		cope.setConvenioId(convenio.getConvenioId());
		copa.setConvenioId(convenio.getConvenioId());
		m.put("cope", cope);
		m.put("copa", copa);
		m.put("ce", ce);
		m.put("convenio", convenio);		
		mostrar(ce.getConvenioId(),model);
			return"form_convenio";
	}
	
	
	
	@RequestMapping(value= "/form_convenio_estudios", method=RequestMethod.POST)
	public String guardar_convevioestudio (@Valid ConvenioEstudio ce, BindingResult result, Convenio convenio ,  Model model, Map<String, Object> m){
		
		
		Convenio aux=null;
		aux=ConvenioDao.findOne(ce.getConvenioId());
		ConvenioPerfil cope =new ConvenioPerfil();
		ConvenioPaquete copa =new ConvenioPaquete();
		cope.setConvenioId(aux.getConvenioId());
		copa.setConvenioId(aux.getConvenioId());
		if (ce.getEstudioTipo().contains("examen")) {
			String[] id = ce.getEstudioTipo().split("examen");
			for (String a : id)
				ce.setEstudioId(Long.parseLong(a));
			ce.setEstudioTipo("examen");
		}
		if (ce.getEstudioTipo().contains("cultivo")) {
			String[] id = ce.getEstudioTipo().split("cultivo");
			for (String a : id)
				ce.setEstudioId(Long.parseLong(a));
			ce.setEstudioTipo("cultivo");
		}
		if (ce.getEstudioTipo().contains("gabinete")) {
			String[] id = ce.getEstudioTipo().split("gabinete");
			for (String a : id)
				ce.setEstudioId(Long.parseLong(a));
			ce.setEstudioTipo("gabinete");
		}
		ConvenioEstudioDao.save(ce);
		m.put("cope", cope);
		m.put("ce", ce);
		m.put("copa", copa);
		m.put("convenio", aux);
		if (EstudioDao.select(ce.getConvenioId())== null  ||  EstudioDao.select(ce.getConvenioId()).isEmpty()) {
			
			model.addAttribute("disableSecondButton", true);
			
		}
		mostrar(aux.getConvenioId(),model);
		return"form_convenio";
	}

	@RequestMapping(value= "/form_convenio_perfiles", method=RequestMethod.POST)
	public String guardar_conveniop (@Valid ConvenioPerfil cope, BindingResult result, Convenio convenio ,  Model model, Map<String, Object> m){
		
		
		Convenio aux=null;
		aux=ConvenioDao.findOne(cope.getConvenioId());
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPaquete copa =new ConvenioPaquete();
		ce.setConvenioId(aux.getConvenioId());
		copa.setConvenioId(aux.getConvenioId());
		CpeDao.save(cope);
		m.put("cope", cope);
		m.put("ce", ce);
		m.put("copa", copa);
		m.put("convenio", aux);
		if (EstudioDao.select(cope.getConvenioId())== null  ||  EstudioDao.select(cope.getConvenioId()).isEmpty()) {
			model.addAttribute("disableSecondButton", true);
		}
		mostrar(aux.getConvenioId(),model);
		return"form_convenio";
	}
	@RequestMapping(value= "/form_convenio_paquetes", method=RequestMethod.POST)
	public String guardar_conveniopa (@Valid ConvenioPaquete copa, BindingResult result, Convenio convenio ,  Model model, Map<String, Object> m){
		
		
		Convenio aux=null;
		aux=ConvenioDao.findOne(copa.getConvenioId());
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPerfil cope =new ConvenioPerfil();
		ce.setConvenioId(aux.getConvenioId());
		cope.setConvenioId(aux.getConvenioId());
		CopaDao.save(copa);
		m.put("cope", cope);
		m.put("ce", ce);
		m.put("copa", copa);
		m.put("convenio", aux);
		if (EstudioDao.select(copa.getConvenioId())== null  ||  EstudioDao.select(copa.getConvenioId()).isEmpty()) {
			model.addAttribute("disableSecondButton", true);
		}
		mostrar(aux.getConvenioId(),model);
		return"form_convenio";
	}
	
	@RequestMapping(value= "/form_convenio_editar/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model, Model m ) {
		Convenio  c = null;
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPerfil cope = new ConvenioPerfil(); 
		ConvenioPaquete copa = new ConvenioPaquete();
		if (id>0) {
			c=ConvenioDao.findOne(id);
		}
		
		else {
			return "redirect:/precios_convenios";
		}

		model.put("titulo", "Editar Convenio");
		model.put("convenio", c);
		ce.setConvenioId(id);
		cope.setConvenioId(id);
		copa.setConvenioId(id);
		model.put("ce", ce);
		model.put("cope", cope);
		model.put("copa", copa);
		if (EstudioDao.select(ce.getConvenioId())== null  ||  EstudioDao.select(ce.getConvenioId()).isEmpty()) {
			
			m.addAttribute("disableSecondButton", true);
			
		}
		mostrar(id,m);
		return "form_convenio";
	}
	
	@RequestMapping (value="/eliminar_convenio/{id}")
	public String eliminar(@PathVariable (value="id") Long id) {
		if (id > 0 )
		{
			ConvenioDao.delete(id);
		}
		
		return "redirect:/precios_convenios";
	}
	
	@RequestMapping (value="/eliminar_ce/{id_ce}/{id_c}/{tipo}")
	public String eliminar_ce(@PathVariable (value="tipo") int tipo,@PathVariable (value="id_ce") Long id_ce,@PathVariable (value="id_c") Long id_c , Map<String, Object> model , Model m,Convenio convenio) {
		
		Convenio aux=null;
		aux=ConvenioDao.findOne(id_c);	
		ConvenioEstudio ce = new ConvenioEstudio();
		ConvenioPerfil cope =new ConvenioPerfil();
		ConvenioPaquete copa =new ConvenioPaquete();
		ce.setConvenioId(aux.getConvenioId());
		cope.setConvenioId(aux.getConvenioId());
		copa.setConvenioId(aux.getConvenioId());
		model.put("cope", cope);
		model.put("copa", copa);
		model.put("ce", ce);
		model.put("convenio", aux);
		if (id_ce > 0 && tipo==1)
		{
			ConvenioEstudioDao.delete(id_ce);
			mostrar(aux.getConvenioId(),m);
			return "form_convenio";	
		}
		if (id_ce > 0 && tipo==2)
		{
			CpeDao.delete(id_ce);
			mostrar(aux.getConvenioId(),m);
			return "form_convenio";	
		}
		if (id_ce > 0 && tipo==3)
		{
			CopaDao.delete(id_ce);
			mostrar(aux.getConvenioId(),m);
			return "form_convenio";	
		}
		
		return "redirect:/precios_convenios";
	}
	
	
	
}
