package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.AppPaciente;
import com.bemedica.springboot.app.repository.AppPacienteRepository;

@Service
public class AppPacienteDaoImpl implements IAppPacienteDao {

	@Autowired
	private EntityManager em;
	@Autowired
	private AppPacienteRepository repository;

	@Override
	public List<AppPaciente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppPaciente findOne(Long PacienteId) {
		// TODO Auto-generated method stub
		return repository.findById(PacienteId).orElse(null);
	}

	@Override
	public void save(AppPaciente paciente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String exist(Long id) {
		// TODO Auto-generated method stub
		String v = em.createNativeQuery("SELECT IF((SELECT COUNT(*) FROM paciente pac \r\n" + "\r\n"
				+ "	INNER JOIN app_paciente apppac ON pac.persona_id = apppac.persona_id\r\n" + "\r\n"
				+ "WHERE apppac.persona_id = " + id + ")>0, 1 , 0);").getSingleResult().toString();
		return v;
	}

}
