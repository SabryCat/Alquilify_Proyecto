package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;

public interface Tipo_usuarioDao {

	List<TiposUsuario> buscarTodos();
	TiposUsuario buscarTipoUsuario(int idTipoUsuario);
}
