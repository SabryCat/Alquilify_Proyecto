package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;


public interface InmuebleRepository extends JpaRepository<Inmueble, Integer>{
	@Query("select i from Inmueble i where i.usuarioAdmin = ?1 ")
	public List<Inmueble> buscarInmueblesAdmin(Usuario administrador);
	
	@Query("select i from Inmueble i where i.usuario = ?1 ")
	public List<Inmueble> buscarInmueblesPropietario(Usuario propietario);
	
}
