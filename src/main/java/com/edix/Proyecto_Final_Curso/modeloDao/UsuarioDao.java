package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface UsuarioDao {
	Usuario buscarUsuario(int idUsuario);
	Usuario altaUsuario(Usuario usuario);
	List<Usuario> buscarTodosPropietarios();
	List<Usuario> buscarTodosInquilinos();
	void eliminarUsuario(int idUsuario);
}
