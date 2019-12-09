package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
@Repository
public class TicketDaoImpl implements ITicketDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findPaciente(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"UPPER(CONCAT(persona.persona_nombre,' ',persona.persona_ap,' ',persona.persona_am)) AS nombre,\r\n" + 
				"IF(TIMESTAMPDIFF(YEAR,persona.persona_fecha_na,CURDATE())>=1,CONCAT(TIMESTAMPDIFF(YEAR,persona.persona_fecha_na,CURDATE()),' AÑO(S)'),IF(TIMESTAMPDIFF(MONTH,persona.persona_fecha_na,CURDATE())>=1,CONCAT(TIMESTAMPDIFF(MONTH,persona.persona_fecha_na,CURDATE()),' MES(ES)'),CONCAT(TIMESTAMPDIFF(DAY,persona.persona_fecha_na,CURDATE()),' DIA(S)'))),\r\n" + 
				"LEFT(persona.persona_genero,1),\r\n" + 
				"IF(direccion.direccion_postal <> '','DOMICILIO CONOCIDO','DOMICILIO DESCONOCIDO')\r\n" + 
				"FROM\r\n" + 
				"persona\r\n" + 
				"INNER JOIN paciente ON paciente.persona_id = persona.persona_id\r\n" + 
				"INNER JOIN direccion ON persona.id_direccion = direccion.direccion_id\r\n" + 
				"INNER JOIN orden ON orden.paciente_id = paciente.paciente_id\r\n" + 
				"WHERE orden.orden_id="+id).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findEmpleado(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"empleados_sucursal.empleado_id_text,\r\n" + 
				"UPPER( CONCAT( persona.persona_nombre,' ', persona.persona_ap,' ', persona.persona_am ) ),\r\n" + 
				"UPPER( CONCAT( p2.persona_nombre,' ', p2.persona_ap,' ', p2.persona_am ) )\r\n" + 
				"FROM\r\n" + 
				"empleados_sucursal\r\n" + 
				"INNER JOIN persona ON empleados_sucursal.persona_id = persona.persona_id\r\n" + 
				"INNER JOIN orden ON orden.empleado_id = empleados_sucursal.empleado_id\r\n" + 
				"INNER JOIN medicos ON orden.medico_id=medicos.medico_id\r\n" + 
				"INNER JOIN persona AS p2 ON medicos.persona_id = p2.persona_id\r\n" + 
				"WHERE orden.orden_id="+id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findFecha(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call fecha_ticket("+id+")").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findServ(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call serv_ticket("+id+")").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findServAll() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call listar_serv()").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Object[]> findTotal(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call total_ticket("+id+")").getResultList();
	}


}
