package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.TipoContrato;
import com.edix.Proyecto_Final_Curso.repository.Tipo_contratoRepository;

@Repository
public class Tipo_contratoDaoImpl implements Tipo_contratoDao{
	@Autowired
	private Tipo_contratoRepository tcrepo;
	@Override
	public List<TipoContrato> buscarTodos() {		
		return tcrepo.findAll();
	}

	@Override
	public TipoContrato buscarTipoContrato(int idTipoContrato) {
		return tcrepo.findById(idTipoContrato).orElse(null);
	}

}
