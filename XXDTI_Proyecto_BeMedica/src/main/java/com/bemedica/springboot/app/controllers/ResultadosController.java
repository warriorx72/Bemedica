package com.bemedica.springboot.app.controllers;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bemedica.springboot.app.models.dao.IResultados;
import com.bemedica.springboot.app.models.entity.Resultados;

@Controller
public class ResultadosController {
	
	
	@Autowired
	@Qualifier("ResultadosDaoJPA")
	private IResultados ResultadosDao;
	
	@RequestMapping (value="/listar_ordenes/{id}", method=RequestMethod.GET)
	public String listar (@PathVariable (value="id") Long id, Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "listar_ordenes";
	}
	
	
	@RequestMapping (value="/guardar_resultado_linea/{id}/{lo}", method=RequestMethod.POST)
	public String guardar (
			@PathVariable (value="id") Long id, 
			@PathVariable (value="lo") Long lo,
			@Valid Resultados resultado,
			Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		
		resultado.setValidacion("1");
		Long auxLineas= resultado.getOrdenEstudioId();
		int i=0;
		ResultadosDao.save(resultado);
		
		List <String> aux  = ResultadosDao.ValidarLinea(auxLineas);
		for (String a : aux) {
			if ( a.equals("1") ) {
				i++;
			}
		}
		if(i== aux.size()) {
			ResultadosDao.Actualizacion_linea(auxLineas);			
		} 
		m.addAttribute("resul",  ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
	
		if (ResultadosDao.LineasOrden(id).size() == ResultadosDao.ValidarOrden(id).size() ){
			 System.out.println("la condicion se cumpple ");
			 ResultadosDao.Actualizacion_Orden(id);
		}
		
		return "listar_ordenes";
	}
	
	
	
	@RequestMapping (value="/agregar_resultado/{id}/{i}/{t}/{lo}", method=RequestMethod.GET)
	public String agregar_resultado (@PathVariable (value="id") Long id, 
			@PathVariable (value="i") Long i,
			@PathVariable (value="t") String tipo,
			@PathVariable (value="lo") Long lo,
			Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		Resultados auxr = new Resultados ();
		m.addAttribute("aux", auxr);
		
		// comenzamos el martirio //
		//Resultado re
		
		if ( ResultadosDao.findAll(lo) == null ||  ResultadosDao.findAll(lo).isEmpty() ){
			
			if (tipo.equals("estudio")){
				List <Object[]> aux  = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados ();
				 for (Object[] a : aux) {
					 
					 resul.setOrdenEstudioId(lo);
					 
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuali("00..00");
					 }
					
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 ResultadosDao.save(resul);
					
					}
				
				//m.addAttribute("resul", ResultadosDao.Estudios(i));
				
				
			
			}
			if (tipo.equals("perfil")){
				
				List <Object[]> aux  = ResultadosDao.Perfil(i);
				Resultados resul = new Resultados ();
				for (Object[] a : aux) {
					 resul.setOrdenEstudioId(lo);
					 
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuali("00..00");
					 }
					 
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 
					 ResultadosDao.save(resul);
					
					}
				//m.addAttribute("resul", ResultadosDao.Perfil(i));
			}
			if (tipo.equals("paquete")){
				
				List <Object[]> aux  = ResultadosDao.Paquete(i);
				Resultados resul = new Resultados ();
				for (Object[] a : aux) {
					 resul.setOrdenEstudioId(lo);
					 
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuali("00..00");
					 }
					 resul.setComentario("");
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 ResultadosDao.save(resul);
					
					}
				//m.addAttribute("resul", ResultadosDao.Paquete(i));
				
				
			}
		
			
		}
		
		m.addAttribute("resul",  ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		
			return "listar_ordenes";
	}
	
	
	
	
	@RequestMapping (value="/ayuda", method=RequestMethod.GET)
	public String ayuda ( Map<String, Object> model, Model m) {
	
		return "ayuda";
	}

}
