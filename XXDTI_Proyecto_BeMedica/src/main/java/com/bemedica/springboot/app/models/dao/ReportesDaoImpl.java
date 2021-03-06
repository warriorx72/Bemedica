package com.bemedica.springboot.app.models.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ReportesDaoJPA")
public class ReportesDaoImpl  implements IReportes{
	
	@PersistenceContext
	private EntityManager em;


	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> estudios_ventas(String fi , String ff, Long cond) {
		
		List<Object[]> re= em.createNativeQuery("{call Reporte ('"+fi+"','"+ff+"',"+cond+")}").getResultList();

															
		 return  re;		 		       	
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> Resultados_imprimir(Long id) {
		List<Object[]> re= em.createNativeQuery("{call resultado ("+id+")}").getResultList();
		 return  re;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Object[]> DatosPaciente(Long id) {
		
		List<Object[]> re= em.createNativeQuery("select \r\n" + 
				"paciente.paciente_id_tex, \r\n" + 
				"CONCAT (persona.persona_ap,' ',persona.persona_am,' ',persona.persona_nombre) as'nombre' ,\r\n" + 
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
	
	
	
}
