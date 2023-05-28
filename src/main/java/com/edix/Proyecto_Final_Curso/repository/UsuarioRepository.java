package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("select u from Usuario u where u.id_alta = ?1 and u.tiposUsuario = 2")
	public List<Usuario> buscarPropietarios(int idUsuario);
	
	@Query("select u from Usuario u where u.id_alta =?1 and u.tiposUsuario = 3")
	public List<Usuario> buscarInquilinos(int idUsuario);
	
	@Query("select u from Usuario u where u.email=?1")
	public Usuario buscarPorEmail(String email);
		
	
}
