package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;

@Repository
public  class VistaOrdenEstudioDaoImpl implements IVistaOrdenEstudioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VistaOrdenEstudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaOrdenEstudio").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override

	public List<VistaOrdenEstudio> voe(Long orden_id) {
		// TODO Auto-generated method stub
		
	
		return em.createQuery("from VistaOrdenEstudio where orden_id="+orden_id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> emp_suc(String rol,String user){
	return em.createNativeQuery("select empleado_vista.empleado_id,sucursal.sucursal_id \r\n" + 
			"from user,user_roles,role,empleado_vista,empleados_sucursal,sucursal \r\n" + 
			"where user.id=user_roles.user_id\r\n" + 
			"and user_roles.role_id=role.id and \r\n" + 
			"user.empleado_id=empleado_vista.empleado_id\r\n" + 
			"and user.empleado_id=empleados_sucursal.empleado_id\r\n" + 
			"and empleado_vista.empleado_id=empleados_sucursal.empleado_id\r\n" + 
			"and empleados_sucursal.sucursal_id=sucursal.sucursal_id\r\n" + 
			"and\r\n" + 
			"user.username=\r\n"+
			""+"'"+user+"'"+
			"and role.description=\r\n"+
			""+"'"+rol+"'").getResultList();
	
	}
	

	

}
