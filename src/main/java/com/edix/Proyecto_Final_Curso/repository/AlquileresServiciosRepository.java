package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;
import com.edix.Proyecto_Final_Curso.entities.Alquilere;

import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection;

public interface AlquileresServiciosRepository extends JpaRepository<AlquilerServicio, Integer> {
	@Query("select a from AlquilerServicio a where a.alquilere = ?1 ")
	public List<AlquilerServicio> buscarPorAlquiler(Alquilere alquiler);
	
	@Query("select a from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and i.usuarioAdmin =?1 ")	
	public List<AlquilerServicio> buscarTodosPorAdministrador(Usuario administrador);
	
	@Query("select a from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and al.usuario =?1 ")	
	public List<AlquilerServicio> buscarTodosPorInquilino(Usuario inquilino);
		
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection(a, DATEDIFF(a.fechaFinalizacion, CURDATE())) from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and i.usuarioAdmin =?1 ")	
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosAdmin(Usuario administrador);
	
	
}
