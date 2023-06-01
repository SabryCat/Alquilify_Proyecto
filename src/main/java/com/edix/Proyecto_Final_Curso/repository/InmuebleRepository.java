package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;


public interface InmuebleRepository extends JpaRepository<Inmueble, Integer>{
	
	/**
	 * Obtener listado de todos los inmuebles dados de alta por un mismo admin
	 * 
	 * @param administrador
	 * @return listado de inmuebles por administrador
	 */
	@Query("select i from Inmueble i where i.usuarioAdmin = ?1 ")
	public List<Inmueble> buscarInmueblesAdmin(Usuario administrador);
	
	
	
	/**
	 * Obtener listado de inmuebles de un mismo propietario
	 * 
	 * @param propietario
	 * @return listado de Inmueble por propietario
	 */
	@Query("select i from Inmueble i where i.usuario = ?1 ")
	public List<Inmueble> buscarInmueblesPropietario(Usuario propietario);

	
	
	/**
	 * Obtener listado de inmuebles alquildados por un mismo inquilino
	 * 
	 * @param inquilino
	 * @return Listado de inmuebles por inquilinos
	 */
	@Query("select i from Inmueble i , Alquilere a where i.idInmueble = a.inmueble"
			+ " and a.usuario = ?1 ")
	public List<Inmueble> buscarInmueblesInquilino(Usuario inquilino);
	
}
