package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface UsuarioDao {
	Usuario buscarByEmail(String email);
	Usuario buscarUsuario(int idUsuario);
	Usuario altaUsuario(Usuario usuario);
	Usuario editarUsuario(Usuario usuario);
	List<Usuario> buscarTodosPropietarios(int idUsuario);
	List<Usuario> buscarTodosInquilinos(int idUsuario);
	List<Usuario> buscarInquilinosPorPropietario(Usuario usuario);
	void eliminarUsuario(int idUsuario);
}
