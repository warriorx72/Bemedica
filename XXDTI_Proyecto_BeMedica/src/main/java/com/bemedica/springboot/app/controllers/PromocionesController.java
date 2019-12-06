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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bemedica.springboot.app.models.dao.IPromocionesDao;
import com.bemedica.springboot.app.models.entity.DireccionE;
import com.bemedica.springboot.app.models.entity.EmpleadosSucursal;
import com.bemedica.springboot.app.models.entity.PersonaE;
import com.bemedica.springboot.app.models.entity.Promociones;


@Controller
public class PromocionesController {
	
	@Autowired
	private IPromocionesDao promocionesDao;
	
	@RequestMapping(value="/precios_promociones")
	public String crear (Map<String, Object> model,Model m)
	{		
		Promociones promo = new Promociones();
		model.put("promo",promo);
		m.addAttribute("promociones", promocionesDao.findAll());
		return "precios_promociones";
	
	}

	@RequestMapping(value= "/form_promo", method=RequestMethod.POST)
	public String guardarPromo (Map<String, Object> m,Promociones promo,BindingResult result , Model model,SessionStatus status)
	{		
		promo.setPromocionIdText("");
		
		promocionesDao.save(promo);
		if(promo.getPromocionIdText()=="") {
			char buf[] = new char[3];
			promo.getPromocionNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			promo.setPromocionIdText(IdText.toLowerCase()+""+(promo.getPromocionId()+10000));
		}
		promocionesDao.save(promo);

		return "redirect:/precios_promociones";
	}
	
	
	@RequestMapping (value="/eliminar_pro/{id}")
	public String eliminar(@PathVariable (value="id") Long id  , RedirectAttributes redirectAttrs) {
		if (id > 0 )
		{
			promocionesDao.delete(id);
			redirectAttrs
            .addFlashAttribute("mensaje", "Empleado eliminado correctamente")
            .addFlashAttribute("clase", "success");
		}
		
		return "redirect:/precios_promociones";
	}
}
