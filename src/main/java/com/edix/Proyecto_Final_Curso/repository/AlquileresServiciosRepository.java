package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;
import com.edix.Proyecto_Final_Curso.entities.Alquilere;

import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection;

public interface AlquileresServiciosRepository extends JpaRepository<AlquilerServicio, Integer> {
	
	
    /**
     * Busca los registros de AlquilerServicio asociados a un objeto Alquilere específico.
     *
     * @param alquiler El objeto Alquilere para el cual se buscarán los registros de AlquilerServicio.
     * @return Una lista de objetos AlquilerServicio que corresponden al Alquilere dado.
     */
	@Query("select a from AlquilerServicio a where a.alquilere = ?1 ")
	public List<AlquilerServicio> buscarPorAlquiler(Alquilere alquiler);
	
    /**
     * Busca todos los registros de AlquilerServicio asociados a un usuario administrador específico.
     *
     * @param administrador El objeto Usuario administrador para el cual se buscarán los registros de AlquilerServicio.
     * @return Una lista de objetos AlquilerServicio que corresponden al usuario administrador dado.
     */	
	@Query("select a from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and i.usuarioAdmin =?1 ")	
	public List<AlquilerServicio> buscarTodosPorAdministrador(Usuario administrador);
	
	
    /**
     * Busca todos los registros de AlquilerServicio asociados a un usuario inquilino específico.
     *
     * @param inquilino El objeto Usuario inquilino para el cual se buscarán los registros de AlquilerServicio.
     * @return Una lista de objetos AlquilerServicio que corresponden al usuario inquilino dado.
     */
	@Query("select a from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and al.usuario =?1 ")	
	public List<AlquilerServicio> buscarTodosPorInquilino(Usuario inquilino);
		
	
    /**
     * Busca los vencimientos de los servicios asociados a un usuario administrador específico.
     *
     * @param administrador El objeto Usuario administrador para el cual se buscarán los vencimientos de servicios.
     * @return Una lista de objetos VencimientoServiciosProjection que corresponden al usuario administrador dado.
     */
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection(a, DATEDIFF(a.fechaFinalizacion, CURDATE())) from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and i.usuarioAdmin =?1 ")	
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosAdmin(Usuario administrador);
	
    /**
     * Busca los vencimientos de los servicios asociados a un usuario propietario específico.
     *
     * @param propietario El objeto Usuario propietario para el cual se buscarán los vencimientos de servicios.
     * @return Una lista de objetos VencimientoServiciosProjection que corresponden al usuario propietario dado.
     */
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection(a, DATEDIFF(a.fechaFinalizacion, CURDATE())) from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and i.usuario =?1 ")	
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosProp(Usuario propietario);
	
	
    /**
     * Busca los vencimientos de los servicios asociados a un usuario inquilino específico.
     *
     * @param inquilino El objeto Usuario inquilino para el cual se buscarán los vencimientos de servicios.
     * @return Una lista de objetos VencimientoServiciosProjection que corresponden al usuario inquilino dado.
     */	
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection(a, DATEDIFF(a.fechaFinalizacion, CURDATE())) from AlquilerServicio a, Alquilere al, Inmueble i "
			+ " where a.alquilere = al.idAlquiler"
			+ " and al.inmueble = i.idInmueble"
			+ " and al.usuario =?1 ")	
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosInqui(Usuario inquilino);
}
