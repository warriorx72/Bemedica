package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Medico;
import com.bemedica.springboot.app.models.entity.Prueba;

@Repository
public class PruebaDaoImpl {

	@PersistenceContext
	private EntityManager em;
	    private List<Prueba> prueba;
	    public List<Prueba> getPrueba() {
	        return prueba;
	    }
	    public void setPrueba(List<Prueba> prueba) {
	        this.prueba = prueba;
	    }
	    @Transactional
		
		public void save(Prueba prueba) {
			if(prueba.getId_prueba() != null && prueba.getId_prueba() >0) {
				em.merge(prueba);
			} else {
				em.persist(prueba);
			}
		}

}
