package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.repository.Tipo_usuarioRepository;

@Repository
public class Tipo_usuarioDaoImpl implements Tipo_usuarioDao{
	@Autowired
	private Tipo_usuarioRepository turepo;
	@Override
	public List<TiposUsuario> buscarTodos() {
		// TODO Auto-generated method stub
		return turepo.findAll();
	}

		
}
