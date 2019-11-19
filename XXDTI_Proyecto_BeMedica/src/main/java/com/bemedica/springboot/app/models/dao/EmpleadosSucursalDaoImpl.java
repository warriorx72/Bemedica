package com.bemedica.springboot.app.models.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bemedica.springboot.app.models.entity.EmpleadoVista;
import com.bemedica.springboot.app.models.entity.EmpleadosSucursal;

@Repository("EmpleadosSucursalDaoJPA")
public class EmpleadosSucursalDaoImpl implements IEmpleadosSucursal {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<EmpleadosSucursal> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from EmpleadosSucursal").getResultList();
		
	}

	@Override
	@Transactional
	public void save(EmpleadosSucursal empleado) {
		if(empleado.getEmpleadoId()  !=null &&  empleado.getEmpleadoId()>0) {
			em.merge(empleado);

		}
		else {
			em.persist(empleado);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public EmpleadosSucursal findOne(Long id) 
	{
		
		return em.find(EmpleadosSucursal.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
			try {
			
				em.getTransaction().begin();
				em.remove(findOne(id));
				em.getTransaction().commit();
				} catch (Exception e) {
				em.close();
				System.out.println("hola mundp"+e);
				}

		//em.remove(findOne(id));	
		
	}
	

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<EmpleadoVista> All() {
		// TODO Auto-generated method stub
		return em.createQuery ("from EmpleadoVista").getResultList();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<EmpleadoVista> filtrado(String filtro) {
		// TODO Auto-generated method stub
		return em.createQuery ("from EmpleadoVista where empleado_estatus="+filtro).getResultList();
		
	}
}
