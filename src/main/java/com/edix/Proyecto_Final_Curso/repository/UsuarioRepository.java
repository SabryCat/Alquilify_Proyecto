package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Usuario;

/**
 * Interface repositorio de usuario
 * 
 * @author sabrycat
 * @version 1.0
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	/**
	 * Obtener listado todos los propietarios que 
	 * hayan sido dados de alta por el mismo adminitrador
	 * 
	 * @param idUsuario
	 * @return listado de propietarios
	 */
	@Query("select u from Usuario u where u.id_alta = ?1 and u.tiposUsuario = 2")
	public List<Usuario> buscarPropietarios(int idUsuario);
	
	
	
	/**
	 * Obtener listado todos los inquilinos que 
	 * hayan sido dados de alta por el mismo adminitrador
	 * 
	 * @param idUsuario
	 * @return listado de inquilinos
	 */
	@Query("select u from Usuario u where u.id_alta =?1 and u.tiposUsuario = 3")
	public List<Usuario> buscarInquilinos(int idUsuario);
		
	
	
	/**
	 * Obtener listado de inquilinos por propietario
	 * usuarios del tipo 3(inquilinos) están en la tabla de alquileres
	 * y el inmueble de la tabla alquileres está en inmuebles
	 * y en la tabla inmuebles el usuario propietario es igual al buscado
	 * 
	 * @param usuario
	 * @return listado de inquilinos
	 */
	@Query("select u from Usuario u, Alquilere a, Inmueble i "
			+ " where u.tiposUsuario = 3 "
			+ " and u.idUsuario = a.usuario"
			+ " and a.inmueble = i.idInmueble"
			+ " and i.usuario =?1")
	public List<Usuario> buscarInquilinosPorPropietario(Usuario usuario);
	
	
	
	/**
	 * Obtiene usuario por email
	 * 
	 * @param email
	 * @return objeto usuario
	 */
	@Query("select u from Usuario u where u.email=?1")
	public Usuario buscarPorEmail(String email);
		
	
}
