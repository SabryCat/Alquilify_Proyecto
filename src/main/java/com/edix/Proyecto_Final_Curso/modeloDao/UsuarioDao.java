package com.edix.Proyecto_Final_Curso.modeloDao;

import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface UsuarioDao {
	Usuario buscarUsuario(int idUsuario);
	Usuario altaUsuario(Usuario usuario);

}
