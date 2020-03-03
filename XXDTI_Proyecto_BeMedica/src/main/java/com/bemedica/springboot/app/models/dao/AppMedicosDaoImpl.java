package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.AppMedicos;
import com.bemedica.springboot.app.repository.AppMedicosRepository;

@Service
public class AppMedicosDaoImpl implements IAppMedicosDao {

	@Autowired
	private EntityManager em;
	@Autowired
	private AppMedicosRepository repository;

	@Override
	public List<AppMedicos> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppMedicos findOne(Long MedicosId) {
		// TODO Auto-generated method stub
		return repository.findById(MedicosId).orElse(null);
	}

	@Override
	public void save(AppMedicos medicos) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Transactional
	@Override
	public String exist(Long id) {
		String val = em.createNativeQuery("SELECT IF((SELECT COUNT(*) FROM medicos med \r\n" + "\r\n"
				+ "	INNER JOIN app_medicos appmed ON med.persona_id = appmed.persona_id\r\n" + "\r\n"
				+ "WHERE appmed.persona_id =" + id + ")>0, 1 , 0)").getSingleResult().toString();
		return val;
	}
}
