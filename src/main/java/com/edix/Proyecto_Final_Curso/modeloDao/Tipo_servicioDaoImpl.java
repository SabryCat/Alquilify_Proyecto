package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.TiposServicio;
import com.edix.Proyecto_Final_Curso.repository.Tipo_servicioRepository;
@Repository
public class Tipo_servicioDaoImpl implements Tipo_servicioDao{
	@Autowired
	private Tipo_servicioRepository tprepo;
	
	@Override
	public List<TiposServicio> buscarTodos() {
		return tprepo.findAll();
	}

	@Override
	public TiposServicio buscarTipoServicio(int idTipoServicio) {
		return tprepo.findById(idTipoServicio).orElse(null);
	}

}
