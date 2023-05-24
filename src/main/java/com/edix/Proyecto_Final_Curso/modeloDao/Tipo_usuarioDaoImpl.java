package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.repository.Tipo_usuarioRepository;

@Service
public class Tipo_usuarioDaoImpl implements Tipo_usuarioDao{
	@Autowired
	private Tipo_usuarioRepository turepo;
	@Override
	public List<TiposUsuario> buscarTodos() {
		return turepo.findAll();
	}
	@Override
	public TiposUsuario buscarTipoUsuario(int idTipoUsuario) {
		return turepo.findById(idTipoUsuario).orElse(null);
	}

		
}
