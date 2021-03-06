package com.bemedica.springboot.app.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.hibernate.Session;
import org.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.comparator.BooleanComparator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bemedica.springboot.app.models.dao.IPersonaDao;
import com.bemedica.springboot.app.models.dao.IPromocionesDao;
import com.bemedica.springboot.app.models.dao.ISucursalDao;
import com.bemedica.springboot.app.models.dao.ITicketDao;
import com.bemedica.springboot.app.models.dao.IVistaEmpleadoDao;
import com.bemedica.springboot.app.models.dao.IMedicoDao;
import com.bemedica.springboot.app.models.dao.IVistaPacienteDao;
import com.bemedica.springboot.app.models.dao.IWebRoleDao;
import com.bemedica.springboot.app.models.dao.IWebUserDao;
import com.bemedica.springboot.app.models.dao.PruebaDaoImpl;
import com.bemedica.springboot.app.models.dao.WebUserDaoImpl;
import com.bemedica.springboot.app.models.dao.IVistaMedicoDao;
import com.bemedica.springboot.app.models.dao.IVistaOrdenDao;
import com.bemedica.springboot.app.models.dao.IVistaOrdenEstudioDao;
import com.bemedica.springboot.app.models.dao.ICatalogoDao;
import com.bemedica.springboot.app.models.dao.IConvenioEstudio;
import com.bemedica.springboot.app.models.dao.IDireccionDao;
import com.bemedica.springboot.app.models.dao.IEstudioDao;
import com.bemedica.springboot.app.models.dao.IOrdenEstudioDao;
import com.bemedica.springboot.app.models.dao.IOrdenEstudioDaoE;
import com.bemedica.springboot.app.models.dao.IPacienteDao;
import com.bemedica.springboot.app.models.dao.IPaquetesDao;
import com.bemedica.springboot.app.models.dao.IPerfilesDao;
import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.dao.IEstudioDao;
import com.bemedica.springboot.app.models.entity.Persona;
import com.bemedica.springboot.app.models.entity.Medico;
import com.bemedica.springboot.app.models.entity.Orden;
import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;
import com.bemedica.springboot.app.models.entity.WebRole;
import com.bemedica.springboot.app.models.entity.WebUser;
import com.bemedica.springboot.app.service.UserService;
import com.bemedica.springboot.app.service.WebUserService;
import com.bemedica.springboot.app.models.entity.OrdenEstudio;
import com.bemedica.springboot.app.models.entity.OrdenEstudioE;
import com.bemedica.springboot.app.models.entity.Estudio;
import com.bemedica.springboot.app.models.entity.EstudioE;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.bemedica.springboot.app.models.entity.Direccion;
import com.bemedica.springboot.app.models.entity.Paciente; //Modelo de entidad para crear objecto de tipo Cliente
import com.bemedica.springboot.app.models.entity.Paquetes;
import com.bemedica.springboot.app.models.entity.Perfiles;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

//.springboot.app.models.service.IPacienteService;   

@Controller
public class PacienteController {

	@Autowired
	private IPersonaDao personaDao; // usar IClienteService con el nombre de clienteService
	@Autowired
	private IDireccionDao direccionDao; /// quitar sino sale
	@Autowired
	private IPacienteDao pacienteDao; /// quitar sino sale
	@Autowired
	private IVistaPacienteDao vistapacienteDao;
	@Autowired
	private IVistaMedicoDao vistamedicoDao;
	@Autowired
	private IMedicoDao medicoDao;
	@Autowired
	private IOrdenDao ordenDao;
	@Autowired
	private IEstudioDao estudioDao;
	@Autowired
	private IOrdenEstudioDao ordenestudioDao;
	@Autowired
	private IVistaEmpleadoDao vistaempleadoDao;
	@Autowired
	private ISucursalDao sucursalDao;
	@Autowired
	private IVistaOrdenEstudioDao vistaordenestudioDao;
	@Autowired
	private IVistaOrdenDao vistaordenDao;
	@Autowired
	private IPaquetesDao paquetesDao;
	@Autowired
	private IPerfilesDao perfilesDao;
	@Autowired
	private ITicketDao ticketDao;
	@Autowired
	private ICatalogoDao catalogoDao;
	@Autowired
	private IOrdenEstudioDaoE orden_estudioE;
	@Autowired
	private IPromocionesDao PromocionDao;
	@Autowired
	private IConvenioEstudio coEsDao;
	@Autowired
	private UserService userService;


	@Autowired
	private IWebUserDao webUser;

	@Autowired
	private IWebRoleDao webRole;



	@Autowired
	private WebUserService webUserService;


	@Autowired
	EntityManager em;



	String password;


	@RequestMapping(value = "/operaciones_recepcion", method = RequestMethod.GET) // vista operaciones_recepcion
	public String operaciones_recepcion(Model model, Map<String, Object> m) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("button_estudio", "disabled");
		model.addAttribute("button_terminar", "disabled");
		// model.addAttribute("personas", personaDao.findAll());
		// model.addAttribute("direcciones", direccionDao.findAll()); ///accede a
		// IClienteService con el nombre clienteService al metodo findALL
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		model.addAttribute("medicos", vistamedicoDao.findAll());
		/// model.addAttribute("estudios", estudioDao.findAll());
		model.addAttribute("empleados", vistaempleadoDao.findAll());
		model.addAttribute("sucursales", sucursalDao.findAll());

		model.addAttribute("ordenes", ordenDao.findAll());
		Orden orden = new Orden();
		OrdenEstudio ordenestudio = new OrdenEstudio();

		// 7direccion.getDireccion_id();
		m.put("orden", orden);/// Modelo entity Cliente para crear un objeto de tipo cliente la palabra
		/// cliente es como llamaremos al objecto desde el ocntrolador
		m.put("ordenestudio", ordenestudio);
		m.put("titulo", "Formulario de Cliente");
		////////////////////////////////////////////////////////////// 7 IMPORTANTE
		////////////////////////////////////////////////////////////// QUITAR SINO SIRVE
		m.put("ordenestudio", ordenestudio);
		Persona personamedico = new Persona();
		m.put("personamedico", personamedico);
		/// model.put("titulo", "Formulario de Cliente");
		//				status.setComplete();
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		model.addAttribute("medicos", vistamedicoDao.findAll());
		/// model.addAttribute("estudios", estudioDao.findAll());
		//		model.addAttribute("estudios", estudioDao.findBy());
		//		////
		//		model.addAttribute("paquetes", paquetesDao.findBy()); ///
		//		model.addAttribute("perfiles", perfilesDao.findBy()); ///
		//// ///
		model.addAttribute("empleados", vistaempleadoDao.findAll()); // 7
		model.addAttribute("sucursales", sucursalDao.findAll());
		model.addAttribute("ordenes", ordenDao.findAll()); //
		model.addAttribute("catalogos", catalogoDao.findAll());
		////////////////////////////////////////////////////////////////////////////// 7
		return "operaciones_recepcion";

	}
	@RequestMapping(value = "/formC")
	public String crear(Map<String, Object> model,Model m) {

		Direccion direccion = new Direccion();
		// 7direccion.getDireccion_id();
		Persona persona = new Persona();
		/// direccion.getDireccion_id();
		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		////////////////////////////////////////////////////////// 7
		Orden orden = new Orden();///////////////////////// crear la orden---------------------------
		////////////////////////////////////////////////////////////////
		model.put("direccion", direccion);/// Modelo entity Cliente para crear un objeto de tipo cliente la palabra
		m.addAttribute("button_estudio", "disabled");
		m.addAttribute("button_terminar", "disabled");							/// cliente es como llamaremos al objecto desde el ocntrolador
		model.put("persona", persona);
		model.put("paciente", paciente);
		model.put("medico", medico);
		model.put("orden", orden);//////////////////////////// 777--------------fdfadf
		model.put("titulo", "Formulario de Cliente");
		return "formC"; ///////////////// PONER OPEREACIONES_RECPECION
	}
	/////////////////////////////////// 77
	//
	/////////////////////////////////////

	/*
	 * @RequestMapping(value = "/operaciones_recepcion") public String
	 * crearorden(Map<String, Object> model) {
	 * 
	 * 
	 * Orden orden= new Orden(); OrdenEstudio ordenestudio=new OrdenEstudio();
	 * //7direccion.getDireccion_id(); model.put("orden", orden);///Modelo entity
	 * Cliente para crear un objeto de tipo cliente la palabra cliente es como
	 * llamaremos al objecto desde el ocntrolador model.put("ordenestudio",
	 * ordenestudio);
	 * 
	 * model.put("titulo", "Formulario de Cliente"); return "operaciones_recepcion";
	 * }
	 */

	@RequestMapping(value = "/guardarorden", method = RequestMethod.POST)
	public String guardarorden(HttpServletRequest request,@Valid Orden orden, BindingResult result, Model model, Map<String, Object> m) {
		///System.out.println(rol.replace("[","").replace("]", ""));




		///System.out.println(hola3[0]);
		///System.out.println(hola3[1]);
		////System.out.println(hola3[0].toString());
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "redirect:operaciones_recepcion";
		}
		// quitar
		if (orden.getPaciente_id() == null && orden.getMedico_id() == null) {
			orden.setOrden_estatus("cotizacion");
		} else {
			orden.setOrden_estatus("pendiente");
		}
		///
		System.out.println("holhola");
		//// orden.setOrden_folio("ORD");
		model.addAttribute("button_estudio", "false");
		model.addAttribute("button_terminar", "disabled");
		orden.setMetodo_pago("efectivo");
		orden.setPromocion_id(0);
		try {
			///String rol2=rol.replace("[", "").replace("]","");
			///System.out.println(rol2);
			///System.out.println(user);

			////String suc=vistaordenestudioDao.emp_suc(rol2, user).get(0).getSucursal_id();
			///System.out.println(( vistaordenestudioDao.emp_suc(rol2, user).toArray()));
			UserController us =new UserController();
			us.UserSucId(request,userService);
			///Object[] hola=vistaordenestudioDao.emp_suc(rol2, user).toArray();
			///System.out.println(hola[0]);
			String id_empleado=us.UserSucId(request, userService)[0];
			String id_sucursal=us.UserSucId(request, userService)[1];
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaa "+id_sucursal);
			///System.out.println("holamundo"+us.UserSucId(request,userService)[0]);


			//Object[] hola3=(Object[]) hola[0];
			orden.setEmpleado_id(id_empleado);
			orden.setSucursal_id(id_sucursal);	
		}
		catch(Exception e) {
			orden.setEmpleado_id(null);
			orden.setSucursal_id(null);
		}



		ordenDao.save(orden);
		orden.setOrden_folio("ORD" + (orden.getOrden_id() + 1000000));
		ordenDao.save(orden);
		OrdenEstudio ordenestudio = new OrdenEstudio();
		ordenestudio.setOrden_id(orden.getOrden_id());
		m.put("ordenestudio", ordenestudio);
		/// model.put("titulo", "Formulario de Cliente");
		//		status.setComplete();
		Mostrar(orden.getOrden_id(),model);
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		String idtext=vistapacienteDao.findAll().get(0).getPaciente_id_tex();
		System.out.println(idtext);

		model.addAttribute("medicos", vistamedicoDao.findAll());
		/// model.addAttribute("estudios", estudioDao.findAll());
		model.addAttribute("estudios", estudioDao.findBy());
		model.addAttribute("cultivos", estudioDao.findCultivo());

		model.addAttribute("paquetes", paquetesDao.findBy());
		model.addAttribute("perfiles", perfilesDao.findBy());
		////
		model.addAttribute("empleados", vistaempleadoDao.findAll());
		model.addAttribute("sucursales", sucursalDao.findAll());
		model.addAttribute("ordenes", ordenDao.findAll());
		if(orden.getOrden_estatus().equals("cotizacion")) {
			model.addAttribute("coti", "disabled");
		}
		else model.addAttribute("coti", "false");
		return "operaciones_recepcion";

	}

	@Override
	public String toString() {
		return "PacienteController [vistaordenestudioDao=" + vistaordenestudioDao + "]";
	}

	public void Mostrar(Long id,Model model) {	
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		model.addAttribute("medicos", vistamedicoDao.findAll());
		model.addAttribute("estudios", estudioDao.findBy());
		model.addAttribute("empleados", vistaempleadoDao.findAll());
		model.addAttribute("sucursales", sucursalDao.findAll());
		model.addAttribute("vista_ordenes", vistaordenDao.findAll(id));
		model.addAttribute("paquetes", paquetesDao.findBy());
		model.addAttribute("perfiles", perfilesDao.findBy());
		model.addAttribute("ordenes", ordenDao.findAll());
		model.addAttribute("cultivos", estudioDao.findCultivo());
		model.addAttribute("gabinetes", estudioDao.findGabinete());
		model.addAttribute("convenios", estudioDao.findConvenio());
		model.addAttribute("antibiogramas", estudioDao.findAntibiograma());
		model.addAttribute("eminfo", ticketDao.findEmpleado(id));
		model.addAttribute("painfo", ticketDao.findPaciente(id));
		model.addAttribute("feinfo", ticketDao.findFecha(id));
		model.addAttribute("seinfo", ticketDao.findServ(id));
		model.addAttribute("toinfo", ticketDao.findTotal(id));
		model.addAttribute("coninfo", ticketDao.findConvenio(id));
		model.addAttribute("orden_estudios", vistaordenestudioDao.voe(id));
		model.addAttribute("orden_monto", vistaordenDao.vo(id));
		model.addAttribute("catalogos", catalogoDao.findAll());
		model.addAttribute("promociones", PromocionDao.findAll());
	}

	@RequestMapping(value = "/form_orden_estudios", method = RequestMethod.POST)
	public String guardar_orden_estudios(Orden orden, Paquetes paquetes, Perfiles perfiles, Paciente paciente,
			OrdenEstudio ordenestudio, Estudio estudio, BindingResult result, Model model, Map<String, Object> m) {

		Orden aux = null;

		aux = ordenDao.findOne(ordenestudio.getOrden_id());
		if (ordenestudio.getEstudio_id().toString().contains("est")) {
			String[] id = ordenestudio.getEstudio_id().split("est");
			for (String a : id)
				ordenestudio.setEstudio_id(a);
			ordenestudio.setTipo("estudio");
			ordenestudioDao.save(ordenestudio);
		}
		if (ordenestudio.getEstudio_id().toString().contains("paq")) {
			String[] id = ordenestudio.getEstudio_id().split("paq");
			for (String a : id)
				ordenestudio.setEstudio_id(a);
			ordenestudio.setTipo("paquete");
			ordenestudioDao.save(ordenestudio);
		}
		if (ordenestudio.getEstudio_id().toString().contains("per")) {
			String[] id = ordenestudio.getEstudio_id().split("per");
			for (String a : id)
				ordenestudio.setEstudio_id(a);
			ordenestudio.setTipo("perfil");
			ordenestudioDao.save(ordenestudio);
		}
		if (ordenestudio.getEstudio_id().toString().contains("cul")) {
			String[] id = ordenestudio.getEstudio_id().split("cul");
			for (String a : id)
				ordenestudio.setEstudio_id(a);
			ordenestudio.setTipo("cultivo");
			ordenestudioDao.save(ordenestudio);
		}
		if (ordenestudio.getEstudio_id().toString().contains("gab")) {
			String[] id = ordenestudio.getEstudio_id().split("gab");
			for (String a : id)
				ordenestudio.setEstudio_id(a);
			ordenestudio.setTipo("gabinete");
			ordenestudioDao.save(ordenestudio);
		}
		if (ordenestudio.getEstudio_id().toString().contains("con")) {
			String[] id = ordenestudio.getEstudio_id().split("con");
			for (Object x:coEsDao.findExa(id[0])) {
				ordenestudio.setEstudio_id(x.toString());
				ordenestudio.setTipo("estudio");
				ordenestudioDao.save(ordenestudio);
			}	
			for (Object x:coEsDao.findGab(id[0])) {
				ordenestudio.setEstudio_id(x.toString());
				ordenestudio.setTipo("gabinete");
				ordenestudioDao.save(ordenestudio);
			}
			for (Object x:coEsDao.findCul(id[0])) {
				ordenestudio.setEstudio_id(x.toString());
				ordenestudio.setTipo("cultivo");
				ordenestudioDao.save(ordenestudio);
			}
			for (Object x:coEsDao.findPaq(id[0])) {
				ordenestudio.setEstudio_id(x.toString());
				ordenestudio.setTipo("paquete");
				ordenestudioDao.save(ordenestudio);
			}
			for (Object x:coEsDao.findPer(id[0])) {
				ordenestudio.setEstudio_id(x.toString());
				ordenestudio.setTipo("perfil");
				ordenestudioDao.save(ordenestudio);
			}
			orden = ordenDao.findOne(ordenestudio.getOrden_id());
			orden.setConvenio_id(id[0]);
			orden.setMonto("100");
			ordenDao.save(orden);
		}




		Mostrar(orden.getOrden_id(),model);
		((Map<String, Object>) model).put("ordenestudio", ordenestudio);
		//// ((Map<String, Object>) model).put("estudio", estudio);
		((Map<String, Object>) model).put("orden", aux);
		/// model.addAttribute("vista_convenio_estudio",
		/// ConvenioEstudioDao.cev(aux.getConvenioId()));
		model.addAttribute("button_estudio", "false");
		if(orden.getConvenio_id()==null) {
			model.addAttribute("button_terminar", "disabled");
		}
		///Primero revisa si es una cotizacion;
		if(aux.getOrden_estatus().equals("cotizacion")) {
			model.addAttribute("tipo_ticket", "block3");
			model.addAttribute("mini_ticket", ""); 
			model.addAttribute("coti", "disabled");
		}
		else {
			///Despues se revisa si es convenio;
			if(aux.getConvenio_id() != null)
			{
				System.out.print("Es un convenio ");
				model.addAttribute("tipo_ticket", "block9"); 
				model.addAttribute("mini_ticket", "block7"); 
				model.addAttribute("coti", "false");
			}
			else
			{
				///Si no, debe ser un ticket normal;
				model.addAttribute("tipo_ticket", "block1"); 
				model.addAttribute("mini_ticket", "block7"); 
				model.addAttribute("coti", "false");
			}
		}


		/// return"form_convenio";
		return "operaciones_recepcion";
	}

	//////////////////////////////////////////////////////////// 7
	// 77
	// 77
	//////////////////////////////////////////////////////////// 7
	//	@RequestMapping(value = "/form2")
	//	public String creardoctor(Map<String, Object> model,Direccion direccion,Persona persona,Medico medico) {
	//		
	//		model.put("direccion", direccion);/// Modelo entity Cliente para crear un objeto de tipo cliente la palabra					
	//		model.put("persona", persona);
	//		model.put("medico", medico);
	//		model.put("titulo", "Formulario de Cliente");
	//		
	//		return "operaciones_recepcion";
	//	}

	@RequestMapping(value = "/formC/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		/// Paciente paciente = null; // asignar a cliente nulo
		Direccion d = null;
		Persona p = null;
		Paciente e = null;

		if (id > 0) {
			/// paciente = pacienteDao.findOne(id); //el objeto cliente es igual a
			/// clienteService=IClienteService METODO FINDONE
			e = pacienteDao.findOne(id);
			p = personaDao.findOne(e.getPersona_id().longValue());
			d = direccionDao.findOne((long) p.getIdDireccion());
		} else {
			return "redirect:/operaciones_recepcion";
		}
		model.put("paciente", e);
		model.put("persona", p);
		model.put("direccion", d);
		// model.put("titulo", "Editar Cliente");
		return "formC";
	}

	@RequestMapping(value = "/form2/{id}")
	public String editar2(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		/// Paciente paciente = null; // asignar a cliente nulo
		Direccion d = null;
		Persona p = null;
		Medico e = null;

		if (id > 0) {
			/// paciente = pacienteDao.findOne(id); //el objeto cliente es igual a
			/// clienteService=IClienteService METODO FINDONE
			e = medicoDao.findOne(id);
			p = personaDao.findOne(e.getPersona_id().longValue());
			d = direccionDao.findOne((long) p.getIdDireccion());
		} else {
			return "redirect:/operaciones_recepcion";
		}
		model.put("medico", e);
		model.put("persona", p);
		model.put("direccion", d);
		// model.put("titulo", "Editar Cliente");
		return "form2";
	}

	@RequestMapping(value = "/formC", method = RequestMethod.POST)
	public String guardar(@RequestParam("email_med") String email_med,@RequestParam("esp_med") String esp_med, @RequestParam("cel_med") String cel_med,
			@RequestParam("persona_nombre_m") String nombre_medico, @RequestParam("persona_am_m") String am_medico,
			@RequestParam("persona_ap_m") String ap_medico, @Valid Direccion direccion, Persona persona,
			Paciente paciente, Medico medico, Orden orden, BindingResult result, Model model, SessionStatus status,
			Map<String, Object> m) {

		String nombre, ap, am;
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "formC";
		}

		direccionDao.save(direccion);
		persona.setIdDireccion(direccion.getDireccion_id().intValue());

		nombre = persona.getPersona_nombre().toUpperCase();
		ap = persona.getPersona_ap().toUpperCase();
		am = persona.getPersona_am().toUpperCase();
		persona.setPersona_nombre(nombre);
		persona.setPersona_ap(ap);
		persona.setPersona_am(am);
		personaDao.save(persona);
		paciente.setPersona_id((long) persona.getPersona_id().intValue());
		paciente.setPaciente_id_tex("PAC" + persona.getPersona_ap().charAt(0) + persona.getPersona_am().charAt(0)
				+ persona.getPersona_nombre().charAt(0) + "" + (persona.getPersona_id() + 100000));

		pacienteDao.save(paciente);
		///////////////////////////////
		/// Persona personamedico = new Persona();
		//// if(personamedico.getPersona_nombre().toString().contains("Med")) {}


		if(!nombre_medico.equals("")) {
			Persona personamedico = new Persona();
			m.put("personamedico", personamedico);

			personamedico.setIdDireccion(direccion.getDireccion_id().intValue());
			personamedico.setPersona_nombre(nombre_medico);
			personamedico.setPersona_email(email_med);
			personamedico.setPersona_tel_cel(cel_med);
			personamedico.setPersona_am(am_medico);
			personamedico.setPersona_ap(ap_medico);
			personaDao.save(personamedico);
			medico.setPersona_id((long) personamedico.getPersona_id().intValue());
			medico.setMedico_especialidad(esp_med);
			medico.setMedico_id_text(
					"MED" + personamedico.getPersona_ap().charAt(0) + personamedico.getPersona_am().charAt(0)
					+ personamedico.getPersona_nombre().charAt(0) + "" + (personamedico.getPersona_id() + 100000));

			medicoDao.save(medico);
			orden.setMedico_id(medico.getMedico_id().intValue());
		}
		/////////////////////////////////////// 77
		orden.setPaciente_id(paciente.getPaciente_id().intValue());//////////////////////////////// 7
		ordenDao.save(orden);
		orden.setOrden_folio("ORD" + (orden.getOrden_id() + 1000000));
		orden.setOrden_estatus("pendiente");
		ordenDao.save(orden);

		/////////////////////////// 7
		OrdenEstudio ordenestudio = new OrdenEstudio();
		ordenestudio.setOrden_id(orden.getOrden_id());
		m.put("ordenestudio", ordenestudio);
		/// model.put("titulo", "Formulario de Cliente");
		//		status.setComplete();
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		model.addAttribute("medicos", vistamedicoDao.findAll());
		/// model.addAttribute("estudios", estudioDao.findAll());
		////
		model.addAttribute("empleados", vistaempleadoDao.findAll());
		model.addAttribute("sucursales", sucursalDao.findAll());
		model.addAttribute("ordenes", ordenDao.findAll());
		model.addAttribute("button_estudio", "disabled");
		model.addAttribute("button_terminar", "disabled");	
		return "operaciones_recepcion";

	}

	@RequestMapping(value = "/formC2", method = RequestMethod.POST)
	public String guardar2(@Valid Direccion direccion, Persona persona,
			Paciente paciente, Medico medico, Orden orden, BindingResult result, Model model, SessionStatus status,
			Map<String, Object> m) {

		String nombre, ap, am;
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "formC";
		}

		direccionDao.save(direccion);
		persona.setIdDireccion(direccion.getDireccion_id().intValue());

		nombre = persona.getPersona_nombre().toUpperCase();
		ap = persona.getPersona_ap().toUpperCase();
		am = persona.getPersona_am().toUpperCase();
		persona.setPersona_nombre(nombre);
		persona.setPersona_ap(ap);
		persona.setPersona_am(am);
		personaDao.save(persona);
		paciente.setPersona_id((long) persona.getPersona_id().intValue());
		paciente.setPaciente_id_tex("PAC" + persona.getPersona_ap().charAt(0) + persona.getPersona_am().charAt(0)
				+ persona.getPersona_nombre().charAt(0) + "" + (persona.getPersona_id() + 100000));

		pacienteDao.save(paciente);
		///////////////////////////////
		/// Persona personamedico = new Persona();
		//// if(personamedico.getPersona_nombre().toString().contains("Med")) {}

		/////////////////////////////////////// 77
		orden.setPaciente_id(paciente.getPaciente_id().intValue());//////////////////////////////// 7
		ordenDao.save(orden);
		orden.setOrden_folio("ORD" + (orden.getOrden_id() + 1000000));
		orden.setOrden_estatus("pendiente");
		ordenDao.save(orden);

		/////////////////////////// 7
		OrdenEstudio ordenestudio = new OrdenEstudio();
		ordenestudio.setOrden_id(orden.getOrden_id());
		m.put("ordenestudio", ordenestudio);
		/// model.put("titulo", "Formulario de Cliente");
		//		status.setComplete();
		model.addAttribute("pacientes", vistapacienteDao.findAll());
		model.addAttribute("medicos", vistamedicoDao.findAll());
		/// model.addAttribute("estudios", estudioDao.findAll());
		////
		model.addAttribute("empleados", vistaempleadoDao.findAll());
		model.addAttribute("sucursales", sucursalDao.findAll());
		model.addAttribute("ordenes", ordenDao.findAll());
		model.addAttribute("button_estudio", "disabled");
		model.addAttribute("button_terminar", "disabled");	
		return "operaciones_recepcion";

	}

	@RequestMapping(value = "/form2", method = RequestMethod.POST)
	public String guardardoctor(Direccion direccion, Persona persona, Medico medico, BindingResult result,
			Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "formC";
		}
		// quitar

		direccionDao.save(direccion);
		persona.setIdDireccion(direccion.getDireccion_id().intValue());
		personaDao.save(persona);
		medicoDao.save(medico);
		status.setComplete();
		return "redirect:operaciones_recepcion";

	}

	@RequestMapping(value = "/eliminar_pa/{id}")
	public String eliminarpa(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			Direccion d = null;
			Persona p = null;
			Paciente e = null;

			e = pacienteDao.findOne(id);
			p = personaDao.findOne(e.getPersona_id().longValue());
			Long idpersona = p.getPersona_id();
			d = direccionDao.findOne((long) p.getIdDireccion());
			Long iddi = d.getDireccion_id();

			pacienteDao.delete(id);
			personaDao.delete(idpersona);
			direccionDao.delete(iddi);
		}
		return "redirect:/operaciones_recepcion";
	}

	@RequestMapping(value = "/eliminar3/{id}/{id_o}")
	public String eliminarpa3(@PathVariable(value = "id") Long id,@PathVariable(value = "id_o") Long id_o, Orden orden, OrdenEstudio ordenestudio,
			Estudio estudio, BindingResult result, Model model, Map<String, Object> m) {

		if (id > 0) {
			ordenestudioDao.delete(id);
			Orden aux = null;

			aux = ordenDao.findOne(id_o);
			ordenestudio.setOrden_id(id_o);
			m.put("ordenestudio", ordenestudio);
			m.put("orden", aux);
			Mostrar(id_o,model);
			///Primero revisa si es una cotizacion;
			if(aux.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
			}
			else {
				///Despues se revisa si es convenio;
				if(aux.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
				else
				{
					///Si no, debe ser un ticket normal;
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
			}
		}
		return "operaciones_recepcion";
	}
	//////////////////////////////////////////////////////////// 7777

	////////////////////////////////////////////////////////////////////77
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @ModelAttribute("PruebaDaoImpl") public PruebaDaoImpl populatePojos() { //
	 * Don't forget to initialize the pojos list or else it won't work PruebaDaoImpl
	 * pruebaForm = new PruebaDaoImpl(); List<Prueba> prueba = new
	 * ArrayList<Prueba>(); for(int i=0; i<2; i++) { prueba.add(new Prueba()); }
	 * pruebaForm.setPrueba(prueba); return pruebaForm; }
	 * 
	 * @RequestMapping(method=RequestMethod.POST) public String
	 * saveForm(@ModelAttribute("pojoForm") PruebaDaoImpl pojoForm) { for(Prueba
	 * prueba : pojoForm.getPrueba()) { PruebaDaoImpl.save(prueba); } return
	 * "theview.jsp"; }
	 */
	@RequestMapping(value = "/form_metodo_pago", method = RequestMethod.POST)
	public String MetodoPago(@RequestParam("id") Long id,@RequestParam() String metodo_pago,Model model,
			RedirectAttributes redirectAttrs, Map<String, Object> m, Orden orden, OrdenEstudio ordenestudio) {
		Orden e =null;

		e = ordenDao.findOne(id);
		if (id > 1) {

			// if(pac_id!=null) {
			// e.setPaciente_id(pac_id);
			// }
			System.out.print("Im here metodo pago ");
			e.setMetodo_pago(metodo_pago);
			// ordenDao.save(e);
			ordenDao.save(e);
			model.addAttribute("button_terminar", "disabled");
			Mostrar(id,model);
			m.put("orden", e);
			ordenestudio.setOrden_id(id);
			m.put("ordenestudio", ordenestudio);
			return "/operaciones_recepcion";
		} else {
			return "redirect:/operaciones_recepcion";
		}

	}


	@RequestMapping(value = "/form_descuento_orden", method = RequestMethod.POST)
	public String descuentoOrden(@RequestParam("id") Long id,@RequestParam() int descuento,Model model,
			RedirectAttributes redirectAttrs, Map<String, Object> m, Orden orden, OrdenEstudio ordenestudio) {
		Orden e =null;

		e = ordenDao.findOne(id);
		if (id > 0) {

			// if(pac_id!=null) {
			// e.setPaciente_id(pac_id);
			// }
			System.out.print("Im here discount");
			e.setPromocion_id(descuento);
			// ordenDao.save(e);
			ordenDao.save(e);
			model.addAttribute("button_terminar", "disabled");
			Mostrar(id,model);
			m.put("orden", e);
			ordenestudio.setOrden_id(id);
			m.put("ordenestudio", ordenestudio);



			if(e.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
			}
			else {
				if(e.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
				else
				{
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
			}


			/*if(e.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
				}
				else {
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}*/


			return "operaciones_recepcion";

		} else {
			return "redirect:/operaciones_recepcion";
		}

	}


	@RequestMapping(value = "/estatus_empleadoPC", method = RequestMethod.POST)
	public String estatusPC(@RequestParam("id") Long id,@RequestParam() String status_id,
			@RequestParam() String pago_inicial, @RequestParam() String pago_final, Model model,
			RedirectAttributes redirectAttrs, Map<String, Object> m, Orden orden, OrdenEstudio ordenestudio) {
		Orden e =null;

		e = ordenDao.findOne(id);
		if (id > 1) {

			// if(pac_id!=null) {
			// e.setPaciente_id(pac_id);
			// }
			e.setOrden_estatus(status_id);
			e.setPago_inicial(pago_inicial);
			e.setPago_final(pago_final);
			// ordenDao.save(e);
			ordenDao.save(e);
			Mostrar(id,model);
			m.put("orden", e);
			ordenestudio.setOrden_id(id);
			m.put("ordenestudio", ordenestudio);
			System.out.print("Hola if "+id);
			//// redirectAttrs
			// .addFlashAttribute("mensaje", "Empleado desactivado ")
			/// .addFlashAttribute("clase", "success");
			return "/operaciones_recepcion";
		} else {
			System.out.print("Hola else "+id);
			/// e.setEmpleadoEstatus("1");
			// EmpleadosSucursalDao.save(e);

			redirectAttrs.addFlashAttribute("mensaje", "Empleado activado").addFlashAttribute("clase", "success");

		}

		return "/administracion_empleados";

	}

	@RequestMapping(value = "/estatus_empleado2", method = RequestMethod.POST)
	public String estatus2(@RequestParam("id") Long id, @RequestParam("status_id") String status_id, Model model,
			RedirectAttributes redirectAttrs) {
		Orden e;

		e = ordenDao.findOne(id.longValue());
		if (id > 1) {

			// e.setPaciente_id(pac_id);
			e.setOrden_estatus(status_id);
			// ordenDao.save(e);
			ordenDao.save(e);
			//// redirectAttrs
			// .addFlashAttribute("mensaje", "Empleado desactivado ")
			/// .addFlashAttribute("clase", "success");
			return "redirect:/operaciones_recepcion";
		} else {
			/// e.setEmpleadoEstatus("1");
			// EmpleadosSucursalDao.save(e);

			redirectAttrs.addFlashAttribute("mensaje", "Empleado activado").addFlashAttribute("clase", "success");

		}

		return "redirect:/administracion_empleados";

	}

	// +++++++++++++++++++++++++++++++++Ticket
	// +++++++++++++++++++++++++++++++++Ticket
	// +++++++++++++++++++++++++++++++++Ticket
	// +++++++++++++++++++++++++++++++++Ticket
	// +++++++++++++++++++++++++++++++++Ticket
	// +++++++++++++++++++++++++++++++++Ticket
	public void esperar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (Exception e) {
			//
		}
	}

	@RequestMapping(value = "/ticket_orden", method = RequestMethod.POST)
	public String ImprimirTicket(Orden orden, BindingResult result, Model model, Map<String, Object> m) {
		esperar(4);
		return "redirect:operaciones_recepcion";

	}

	@RequestMapping(value = "/form_pago", method = RequestMethod.POST)
	public String FormPago(Orden orden, BindingResult result, Model model, Map<String, Object> m,
			@RequestParam("id") Long id,@RequestParam("primer_pago") String primer_pago,OrdenEstudio ordenestudio) {
		if(id>0) {



			orden = ordenDao.findOne(id);

			String flag= webUser.Exist(Long.valueOf(orden.getPaciente_id()));








			if (orden.getConvenio_id() == null && flag.equals("0")) {




				System.out.println("aqui imprime que pedooooo"+flag);




				WebUser wu= new WebUser();


				Paciente pac= new Paciente ();
				WebRole roli= new WebRole();







				roli = webRole.findOne(1L);


				pac = pacienteDao.findOne(Long.valueOf(orden.getPaciente_id()));
				//Random aleatorio= new Random();
				//int alet =100000000+ aleatorio.nextInt(800000000);
				//System.out.println("password:"+alet);


				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				String pwd = RandomStringUtils.random( 15, characters );
				password=pwd;


				System.out.println("Password:" +password);
				wu.setExtra(pwd);
				wu.setUserPassword(String.valueOf(pwd));
				wu.setPaciente_id(orden.getPaciente_id());
				wu.setUser_name(pac.paciente_id_tex);
				//wu.setUser_name(pac.);
				wu.setUser_status("1");
				wu.setTipo("paciente");



				roli.getUser().add(wu);

				wu.getWebroles().add(roli);

				webUser.save(wu);


			} 




			System.out.println("aqui imprime que pedooooo con flag positivo"+flag);



			orden = ordenDao.findOne(id);
			orden.setPago_inicial(primer_pago);
			ordenDao.save(orden);
			ordenestudio.setOrden_id(id);
			m.put("ordenestudio", ordenestudio);
			m.put("orden", orden);
			Mostrar(id,model);


			if(orden.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
				model.addAttribute("password", password);
			}
			else {
				if(orden.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");


					//quitar esto en caso de que truene

					if (flag.equals("0")) {



						model.addAttribute("password", password);

					} else {

						WebUser wu= webUserService.findBypaciente(orden.paciente_id);

						System.out.println("ultimo ultimo" + orden.paciente_id);
						System.out.println("ultimo ultimo" +webUserService.findBypaciente(orden.paciente_id ));
						System.out.println("ultimo ultimo" + wu.getExtra());
						String pass= wu.getExtra();
						model.addAttribute("password", pass);
					}


					///aqui termina pero debes dejar el model atribbute


				}
				else
				{
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");

					//quitar esto en caso de que truene

					if (flag.equals("0")) {



						model.addAttribute("password", password);

					} else {

						WebUser wu= webUserService.findBypaciente(orden.paciente_id);

						System.out.println("ultimo ultimo" + orden.paciente_id);


						System.out.println("ultimo ultimo" +webUserService.findBypaciente(orden.paciente_id ));
						System.out.println("ultimo ultimo" + wu.getExtra());
						String pass= wu.getExtra();
						model.addAttribute("password", pass);
					}


					///aqui termina pero debes dejar el model atribbute

				}
			}


			return "operaciones_recepcion";
		}
		else {
			return "redirect:operaciones_recepcion";
		}
	}

	@RequestMapping(value = "/liquidar_pago", method = RequestMethod.POST)
	public String LiquidarPago(Orden orden, BindingResult result, Model model, Map<String, Object> m,
			@RequestParam("id") Long id,OrdenEstudio ordenestudio) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();





		if(id>0) {




			orden = ordenDao.findOne(id);

			String flag= webUser.Exist(Long.valueOf(orden.getPaciente_id()));








			if (orden.getConvenio_id() == null && flag.equals("0")) {




				System.out.println("aqui imprime que pedooooo"+flag);




				WebUser wu= new WebUser();


				Paciente pac= new Paciente ();
				WebRole roli= new WebRole();







				roli = webRole.findOne(1L);


				pac = pacienteDao.findOne(Long.valueOf(orden.getPaciente_id()));
				//Random aleatorio= new Random();
				//int alet =100000000+ aleatorio.nextInt(800000000);
				//System.out.println("password:"+alet);




				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				String pwd = RandomStringUtils.random( 15, characters );


				password=pwd;


				System.out.println("Password:" +password);
				wu.setExtra(pwd);
				wu.setUserPassword(String.valueOf(pwd));
				wu.setPaciente_id(orden.getPaciente_id());
				wu.setUser_name(pac.paciente_id_tex);
				wu.setUser_status("1");
				wu.setTipo("paciente");



				roli.getUser().add(wu);

				wu.getWebroles().add(roli);

				webUser.save(wu);


			} 




			System.out.println("aqui imprime que pedooooo con flag positivo"+flag);








			orden = ordenDao.findOne(id);
			orden.setPago_final(orden.getMonto());
			orden.setPago_inicial("0");
			orden.setOrden_estatus("Pagado");
			ordenDao.save(orden);
			ordenestudio.setOrden_id(id);
			orden.setFecha_liquidacion(dtf.format(now));
			m.put("ordenestudio", ordenestudio);
			m.put("orden", orden);
			Mostrar(id,model);


			if(orden.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
				//quitar esto en caso de que truene



				if (flag.equals("0")) {



					model.addAttribute("password", password);

				} else {

					WebUser wu= webUserService.findBypaciente(orden.paciente_id);

					System.out.println("ultimo ultimo" + orden.paciente_id);


					System.out.println("ultimo ultimo" +webUserService.findBypaciente(orden.paciente_id ));
					System.out.println("ultimo ultimo" + wu.getExtra());
					String pass= wu.getExtra();
					model.addAttribute("password", pass);

				}


				///aqui termina pero debes dejar el model atribbute
			}
			else {
				if(orden.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");

					//quitar esto en caso de que truene



					if (flag.equals("0")) {



						model.addAttribute("password", password);

					} else {

						WebUser wu= webUserService.findBypaciente(orden.paciente_id);

						System.out.println("ultimo ultimo" + orden.paciente_id);


						System.out.println("ultimo ultimo" +webUserService.findBypaciente(orden.paciente_id ));
						System.out.println("ultimo ultimo" + wu.getExtra());
						String pass= wu.getExtra();
						model.addAttribute("password", pass);

					}


					///aqui termina pero debes dejar el model atribbute

				}
				else
				{
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");


					//quitar esto en caso de que truene

					if (flag.equals("0")) {



						model.addAttribute("password", password);

					} else {


						//orden = ordenDao.findOne(id);



						WebUser wu= webUserService.findBypaciente(orden.paciente_id);

						System.out.println("ultimo ultimo" + orden.paciente_id);


						System.out.println("ultimo ultimo" +webUserService.findBypaciente(orden.paciente_id ));
						System.out.println("ultimo ultimo" + wu.getExtra());
						String pass= wu.getExtra();
						model.addAttribute("password", pass);

					}


					///aqui termina pero debes dejar el model atribbute
				}
			}

			return "operaciones_recepcion";
		}
		else {
			return "redirect:operaciones_recepcion";
		}
	}


	@RequestMapping(value= "/linea_porcentaje", method=RequestMethod.POST)
	public String porcentaje (
			@RequestParam("id") Long id, 
			@RequestParam("porcentaje") Double porcentaje,
			@RequestParam("linea") Long linea,
			OrdenEstudio ordenestudio,
			Model model , Map<String, Object> mo){
		if(id>0) {
			OrdenEstudioE aux = new OrdenEstudioE();
			aux= orden_estudioE.findOne(id);

			aux.setDescuento(((porcentaje/100)*aux.getTotalLinea()));

			//aux.setTotalLinea(((porcentaje/100)*aux.getTotalLinea())+porcentaje);
			orden_estudioE.save(aux);
			Orden orden = null;
			orden = ordenDao.findOne(linea);
			ordenestudio.setOrden_id(linea);
			mo.put("ordenestudio", ordenestudio);
			mo.put("orden", orden);
			Mostrar(linea,model);
			model.addAttribute("button_terminar", "disabled");
			if(orden.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
			}
			else {
				if(orden.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
				else
				{
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
			}
		}
		return "operaciones_recepcion";
	}


	@RequestMapping(value= "/linea_descuento", method=RequestMethod.POST)
	public String descuento (
			@RequestParam("id") Long id, 
			@RequestParam("descuento") Double descuento,
			@RequestParam("linea") Long linea,
			OrdenEstudio ordenestudio,
			Model model , Map<String, Object> mo){
		if(id>0) {
			OrdenEstudioE aux = new OrdenEstudioE();
			aux= orden_estudioE.findOne(id);
			aux.setDescuento(descuento);
			orden_estudioE.save(aux);
			Orden orden = null;
			orden = ordenDao.findOne(linea);
			ordenestudio.setOrden_id(linea);
			mo.put("ordenestudio", ordenestudio);
			mo.put("orden", orden);
			model.addAttribute("button_terminar", "disabled");
			Mostrar(linea,model);
			if(orden.getOrden_estatus().equals("cotizacion")) {
				model.addAttribute("tipo_ticket", "block3");
				model.addAttribute("mini_ticket", ""); 
				model.addAttribute("coti", "disabled");
			}
			else {
				if(orden.getConvenio_id() != null)
				{
					System.out.print("Es un convenio ");
					model.addAttribute("tipo_ticket", "block9"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
				else
				{
					model.addAttribute("tipo_ticket", "block1"); 
					model.addAttribute("mini_ticket", "block7"); 
					model.addAttribute("coti", "false");
				}
			}
		}
		return "operaciones_recepcion";
	}


}
