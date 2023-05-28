package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface AlquileresRepository extends JpaRepository<Alquilere, Integer>{
	@Query("select a from Alquilere a, Inmueble i where a.inmueble = i.idInmueble AND i.usuarioAdmin = ?1 ")
	public List<Alquilere> buscarInmueblesAdmin(Usuario administrador);
}
