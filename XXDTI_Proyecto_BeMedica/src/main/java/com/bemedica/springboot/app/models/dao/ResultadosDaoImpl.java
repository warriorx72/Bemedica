package com.bemedica.springboot.app.models.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bemedica.springboot.app.models.entity.Resultados;


@Repository("ResultadosDaoJPA")
public class ResultadosDaoImpl implements IResultados {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Resultados> findAll(Long id) {
		return em.createQuery ("from Resultados where orden_estudio_id="+id).getResultList();
	}
	
	
	@Override
	@Transactional
	public void save(Resultados resultados) {
		if(resultados.getResultadoId()  !=null &&  resultados.getResultadoId()>0) {
			em.merge(resultados);
		}
		else {
			em.persist(resultados);
		}
	}
	
	@Override
	@Transactional(readOnly= true)
	public Resultados findOne(Long id) {
		return em.find(Resultados.class,id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> PacienteOrden(Long id) {
		List<Object[]> re= em.createNativeQuery("select \r\n" + 
				"paciente.paciente_id_tex, \r\n" + 
				"persona.persona_nombre,\r\n" + 
				"TIMESTAMPDIFF(YEAR,persona.persona_fecha_na,CURDATE()) as 'años',\r\n" + 
				"timestampdiff(month,persona.persona_fecha_na,curdate())as  'meses', \r\n" + 
				"persona.persona_genero,\r\n" + 
				"orden.orden_fecha, \r\n" + 
				"orden.orden_folio,\r\n" + 
				"(select concat(pm.persona_nombre ,' ', pm.persona_ap ,' ', pm.persona_am) from orden , persona pm , medicos where orden.medico_id= medicos.medico_id \r\n" + 
				"and medicos.persona_id=pm.persona_id and orden.orden_id="+id+")as 'medico_nombre' ,\r\n" + 
				"concat (pe.persona_nombre ,' ', pe.persona_ap,' ', pe.persona_am) as 'empleado',\r\n" + 
				"orden.orden_estatus\r\n" + 
				"from paciente,persona,orden  , persona pe , empleados_sucursal  where \r\n" + 
				"1=1\r\n" + 
				"and orden.paciente_id = paciente.paciente_id\r\n" + 
				"and paciente.persona_id=persona.persona_id\r\n" + 
				"and orden.empleado_id= empleados_sucursal.empleado_id\r\n" + 
				"and empleados_sucursal.persona_id=pe.persona_id\r\n" + 
				"and orden.orden_id="+id).getResultList();
		return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> LineasOrden(Long id) {
		List<Object[]> re= em.createNativeQuery("select \r\n" + 
				"`test`.`orden`.`orden_id` AS `orden_id`,\r\n" + 
				"`test`.`orden`.`orden_folio` AS `orden_folio`,\r\n" + 
				"`test`.`estudios`.`estudio_id_text` AS `estudio_id_text`,\r\n" + 
				"`test`.`estudios`.`estudio_nombre` AS `estudio_nombre`,\r\n" + 
				"`test`.`orden_estudio`.`orden_estudio_id` AS `orden_estudio_id`,\r\n" + 
				"`test`.`orden`.`orden_estatus` AS `orden_estatus`, \r\n" + 
				" `test`.`orden_estudio`.`captura` AS `captura`,\r\n" + 
				" `test`.`estudios`.`estudio_id` AS `id`,\r\n" + 
				" `test`.`orden_estudio`.`tipo` AS `tipo` \r\n" + 
				"from `test`.`orden` join `test`.`estudios` join `test`.`orden_estudio` where `test`.`estudios`.`estudio_id` = `test`.`orden_estudio`.`estudio_id` and `test`.`orden`.`orden_id` = `test`.`orden_estudio`.`orden_id` and `test`.`orden_estudio`.`tipo` = 'estudio'\r\n" + 
				"and `test`.`orden`.`orden_id`="+id+"\r\n" + 
				"union select \r\n" + 
				"`test`.`orden`.`orden_id` AS `orden_id`,\r\n" + 
				"`test`.`orden`.`orden_folio` AS `orden_folio`,\r\n" + 
				"`test`.`paquetes`.`paquete_id_text` AS `paquete_id_text`,\r\n" + 
				"`test`.`paquetes`.`paquete_nombre` AS `paquete_nombre`,\r\n" + 
				"`test`.`orden_estudio`.`orden_estudio_id` AS `orden_estudio_id`,\r\n" + 
				"`test`.`orden`.`orden_estatus` AS `orden_estatus`, \r\n" + 
				"`test`.`orden_estudio`.`captura` AS `captura`, \r\n" + 
				"`test`.`paquetes`.`paquete_id` AS `id`,\r\n" + 
				" `test`.`orden_estudio`.`tipo` AS `tipo` \r\n" + 
				"from `test`.`orden` join `test`.`paquetes` join `test`.`orden_estudio` where `test`.`paquetes`.`paquete_id` = `test`.`orden_estudio`.`estudio_id` and `test`.`orden`.`orden_id` = `test`.`orden_estudio`.`orden_id` and `test`.`orden_estudio`.`tipo` = 'paquete'\r\n" + 
				"and `test`.`orden`.`orden_id` ="+id+"\r\n" + 
				"union select \r\n" + 
				"`test`.`orden`.`orden_id` AS `orden_id`,\r\n" + 
				"`test`.`orden`.`orden_folio` AS `orden_folio`,\r\n" + 
				"`test`.`perfiles`.`perfil_id_text` AS `perfil_id_text`,\r\n" + 
				"`test`.`perfiles`.`perfil_nombre` AS `perfil_nombre`,\r\n" + 
				"`test`.`orden_estudio`.`orden_estudio_id` AS `orden_estudio_id`,\r\n" + 
				"`test`.`orden`.`orden_estatus` AS `orden_estatus`, \r\n" + 
				"`test`.`orden_estudio`.`captura` AS `captura`, \r\n" + 
				"`test`.`perfiles`.`perfil_id` AS `id`,\r\n" + 
				" `test`.`orden_estudio`.`tipo` AS `tipo` \r\n" + 
				"from `test`.`orden` join `test`.`perfiles` join `test`.`orden_estudio` where `test`.`perfiles`.`perfil_id` = `test`.`orden_estudio`.`estudio_id` and `test`.`orden`.`orden_id` = `test`.`orden_estudio`.`orden_id` and `test`.`orden_estudio`.`tipo` = 'perfil'\r\n" + 
				"and `test`.`orden`.`orden_id` ="+id).getResultList();
		return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> Estudios(Long id) {
		List<Object[]> re= em.createNativeQuery(""
				+ "select "
				+ "estudios.estudio_id , "
				+ "estudios.estudio_nombre , "
				+ "estudios.estudio_unidades_res from \r\n" + 
				"estudios \r\n" + 
				"where 1=1\r\n" + 
				"and estudios.estudio_id="+id).getResultList();
		return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> Perfil(Long id) {
		List<Object[]> re= em.createNativeQuery("select estudios.estudio_id,estudios.estudio_nombre, estudios.estudio_unidades_res from estudios, perfiles_estudios \r\n" + 
				"where \r\n" + 
				"1=1 \r\n" + 
				"and perfiles_estudios.estudio_id= estudios.estudio_id \r\n" + 
				"and perfiles_estudios.perfil_id="+id).getResultList();
		return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> Paquete(Long id) {
		
		
		List<Object[]> re= em.createNativeQuery("select estudios.estudio_id , estudios.estudio_nombre , estudios.estudio_unidades_res from \r\n" + 
				"estudios, paquetes_estudios \r\n" + 
				"where 1=1 \r\n" + 
				"and paquetes_estudios.estudio_id= estudios.estudio_id\r\n" + 
				"and paquetes_estudios.paquete_id="+id+"\r\n" + 
				"union all\r\n" + 
				"SELECT \r\n" + 
				"estudios.estudio_id,estudios.estudio_nombre, estudios.estudio_unidades_res from estudios, paquetes_perfiles , perfiles_estudios\r\n" + 
				"where \r\n" + 
				"1=1 \r\n" + 
				"and paquetes_perfiles.perfil_id = perfiles_estudios.perfil_id\r\n" + 
				"and perfiles_estudios.estudio_id= estudios.estudio_id\r\n" + 
				"and paquetes_perfiles.paquete_id="+id).getResultList();
		return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<String> ValidarLinea(Long id) {	
		List<String> re= em.createNativeQuery("select resultados.validacion from resultados where resultados.orden_estudio_id="+id).getResultList();
		return re;
	}
	
	
	@Override
	@Transactional
	public void Actualizacion_linea(Long id) {
		
		 em.createQuery(
			      "UPDATE OrdenEstudio SET captura = 'Capturado' " +
			      "WHERE orden_estudio_id = "+id).executeUpdate();
			
	
	}



	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List <String>  ValidarOrden(Long id) {
		
		
		List<String> re= em.createNativeQuery("SELECT orden_estudio.captura from orden_estudio \r\n" + 
				"where 1=1 \r\n" + 
				"and orden_estudio.orden_id ="+id+"\r\n" + 
				"and orden_estudio.captura='Capturado'").getResultList();
		return re;
	}




	@Override
	@Transactional
	public void Actualizacion_Orden(Long id) {
		em.createQuery(
			      "UPDATE Orden SET orden_estatus = 'A entrega' " +
			      "WHERE orden_id = "+id).executeUpdate();
		
	}

}
