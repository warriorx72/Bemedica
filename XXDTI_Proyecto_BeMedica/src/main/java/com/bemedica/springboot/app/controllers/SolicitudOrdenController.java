package com.bemedica.springboot.app.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedica.springboot.app.models.dao.IAppMedicosDao;
import com.bemedica.springboot.app.models.dao.IAppPacienteDao;
import com.bemedica.springboot.app.models.dao.ICatalogoDao;
import com.bemedica.springboot.app.models.dao.IConvenioEstudio;
import com.bemedica.springboot.app.models.dao.IDireccionDao;
import com.bemedica.springboot.app.models.dao.IEstudioDao;
import com.bemedica.springboot.app.models.dao.IMedicoDao;
import com.bemedica.springboot.app.models.dao.IMedicosDao;
import com.bemedica.springboot.app.models.dao.IOrdenDao;
import com.bemedica.springboot.app.models.dao.IOrdenEstudioDao;
import com.bemedica.springboot.app.models.dao.IOrdenEstudioDaoE;
import com.bemedica.springboot.app.models.dao.IPacienteDao;
import com.bemedica.springboot.app.models.dao.IPaquetesDao;
import com.bemedica.springboot.app.models.dao.IPerfilesDao;
import com.bemedica.springboot.app.models.dao.IPersonaDao;
import com.bemedica.springboot.app.models.dao.IPromocionesDao;
import com.bemedica.springboot.app.models.dao.ISolicitudDetallesDao;
import com.bemedica.springboot.app.models.dao.ISolicitudOrdenDao;
import com.bemedica.springboot.app.models.dao.ISucursalDao;
import com.bemedica.springboot.app.models.dao.ITicketDao;
import com.bemedica.springboot.app.models.dao.IVistaEmpleadoDao;
import com.bemedica.springboot.app.models.dao.IVistaMedicoDao;
import com.bemedica.springboot.app.models.dao.IVistaOrdenDao;
import com.bemedica.springboot.app.models.dao.IVistaOrdenEstudioDao;
import com.bemedica.springboot.app.models.dao.IVistaPacienteDao;
import com.bemedica.springboot.app.models.entity.AppMedicos;
import com.bemedica.springboot.app.models.entity.AppPaciente;
import com.bemedica.springboot.app.models.entity.Medico;
import com.bemedica.springboot.app.models.entity.Medicos;
import com.bemedica.springboot.app.models.entity.Orden;
import com.bemedica.springboot.app.models.entity.OrdenEstudio;
import com.bemedica.springboot.app.models.entity.Paciente;
import com.bemedica.springboot.app.models.entity.Persona;
import com.bemedica.springboot.app.models.entity.SolicitudDetalles;
import com.bemedica.springboot.app.models.entity.SolicitudOrden;
import com.bemedica.springboot.app.service.UserService;

@Controller
public class SolicitudOrdenController {
	@Autowired
	private ISolicitudOrdenDao solicitudDao;
	@Autowired
	private IOrdenDao ordenDao;
	@Autowired
	private IAppMedicosDao appDao;
	@Autowired
	private IMedicosDao medicosDao;
	@Autowired
	private IPersonaDao personaDao;
	@Autowired
	private IAppPacienteDao pacienteDao;
	@Autowired
	private IPacienteDao pacDao;
	@Autowired
	private ISolicitudDetallesDao SolicitudDetallesDao;
	// usar IClienteService con el nombre de clienteService
	@Autowired
	private IDireccionDao direccionDao; /// quitar sino sale
/// quitar sino sale
	@Autowired
	private IVistaPacienteDao vistapacienteDao;
	@Autowired
	private IVistaMedicoDao vistamedicoDao;
	@Autowired
	private IMedicoDao medicoDao;

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
	IOrdenEstudioDao ordenEstudioDao;

	public String i;
	public String p;
	public Long ordenid;

	@RequestMapping(value = "solicitud_estudios", method = RequestMethod.GET)
	public String listar(Map<String, Object> m, Model model) {
		model.addAttribute("solicitudes", solicitudDao.findAllSol());
		return "solicitud_estudios";
	}

	@RequestMapping(value = "/eliminar_solicitud/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		SolicitudOrden sol = solicitudDao.findOne(id);
		if (id > 0) {
			sol.setEstatus("2");
			solicitudDao.save(sol);
		}
		return "redirect:/solicitud_estudios";
	}

	public void Mostrar(Long id, Model model) {
		System.out.println(id+"ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
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

	@RequestMapping(value = "/agregar_solicitud/{id}")
	public String agregar(@PathVariable(value = "id") Long id, Medico medico, Long PersonaId, HttpServletRequest request,
			Model model, Map<String, Object> m) {
		
		
		Medicos medicos = new Medicos();
		Paciente paciente = new Paciente();
		UserController user = new UserController();
		Orden orden = new Orden();
		OrdenEstudio ordenEstudio = new OrdenEstudio();
		SolicitudOrden sol = solicitudDao.findOne(id);
		AppMedicos app = appDao.findOne(sol.getMedico_id());
		Persona persona = personaDao.findOne(app.getPersona().getPersona_id());
		AppPaciente ap = pacienteDao.findOne(sol.getPaciente_id());
		String empleado = user.UserSucId(request, userService)[0];
		String sucursal = user.UserSucId(request, userService)[1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		
		if (appDao.exist(app.getPersona().getPersona_id()).equals("0")) {
			medicos.setMedicoIdText("MED" + persona.getPersona_ap().charAt(0) + persona.getPersona_am().charAt(0)
					+ persona.getPersona_nombre().charAt(0) + (persona.getPersona_id() + 100000));
			medicos.setMedicoEspecialidad(app.getMedico_especialidad());
			medicos.setMedicoCedula(app.getMedico_cedula());
			medicos.setPersonaId(app.getPersona().getPersona_id());
			medicosDao.save(medicos);
			i = medicosDao.findByPersona(app.getPersona().getPersona_id()).toString();
		}

		if (appDao.exist(app.getPersona().getPersona_id()).equals("1")) {
			i = medicosDao.findByPersona(app.getPersona().getPersona_id()).toString();
		}

		if (pacienteDao.exist(ap.getPersona_id()).equals("0")) {
			paciente.setPersona_id(ap.getPersona_id());
			paciente.setPaciente_id_tex("PAC" + persona.getPersona_ap().charAt(0) + persona.getPersona_am().charAt(0)
					+ persona.getPersona_nombre().charAt(0) + (persona.getPersona_id() + 100000));
			pacDao.save(paciente);
			p = pacDao.findByPersona(ap.getPersona_id()).toString();
		}

		if (pacienteDao.exist(ap.getPersona_id()).equals("1")) {
			p = pacDao.findByPersona(ap.getPersona_id()).toString();

		}

		if (id > 0) {
			pacDao.findOne(id);
			orden.setPaciente_id(Integer.parseInt(p));
			orden.setEmpleado_id(empleado);
			orden.setSucursal_id(sucursal);
			orden.setOrden_estatus("pendiente");
			orden.setPromocion_id(0);
			orden.setOrden_fecha(dtf.format(now));
			orden.setMetodo_pago("efectivo");
			orden.setMedico_id(Integer.parseInt(i));
			ordenDao.save(orden);
			orden.setOrden_folio("ORD" + (orden.getOrden_id() + 1000000));
			ordenDao.save(orden);
		}

		List<Object[]> detalles = SolicitudDetallesDao.findAllById(id);
		for (Object[] detalle : detalles) {

			ordenEstudio.setEstudio_id(detalle[0].toString());
			ordenEstudio.setOrden_id(Long.parseLong(detalle[1].toString()));

			if (detalle[2].equals("Examenes")) {
				ordenEstudio.setTipo("estudio");
			}
			if (detalle[2].equals("Cultivos")) {
				ordenEstudio.setTipo("cultivo");
			}
			if (detalle[2].equals("Gabinete")) {
				ordenEstudio.setTipo("gabinete");
			}
			if (detalle[2].equals("Paquetes")) {
				ordenEstudio.setTipo("paquete");
			}
			if (detalle[2].equals("Perfiles")) {
				ordenEstudio.setTipo("perfil");
			}

			ordenEstudioDao.save(ordenEstudio);

			sol.setEstatus("1");
			solicitudDao.save(sol);
			ordenEstudio.setOrden_id(orden.getOrden_id());
			

			System.out.println(orden.getOrden_id()+"wertyuewqwertyewertywhyteryhtwetrerter");
			model.addAttribute("pacientes", vistapacienteDao.findAll());
			model.addAttribute("medicos", vistamedicoDao.findAll());
			
			m.put("ordenestudio", ordenEstudio);
			m.put("orden", orden);
		}
		Mostrar(orden.getOrden_id(), model);

		return "/operaciones_recepcion";
	}

}
//+'/'+${solicitud[7]}+'/'+${solicitud[8]}