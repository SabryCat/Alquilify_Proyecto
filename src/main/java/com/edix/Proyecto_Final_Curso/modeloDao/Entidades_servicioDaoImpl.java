package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.EntidadesServicio;
import com.edix.Proyecto_Final_Curso.repository.Entidades_servicioRepository;
@Repository
public class Entidades_servicioDaoImpl implements Entidades_servicioDao{
	@Autowired
	private Entidades_servicioRepository esrepo;
	@Override
	public List<EntidadesServicio> buscarTodos() {
		return esrepo.findAll();
	}

	@Override
	public EntidadesServicio buscarEntidadServicio(int idEntidadServicio) {
		return esrepo.findById(idEntidadServicio).orElse(null);
	}

}
