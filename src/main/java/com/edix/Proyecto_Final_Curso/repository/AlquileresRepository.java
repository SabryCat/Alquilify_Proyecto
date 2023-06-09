package com.edix.Proyecto_Final_Curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.VencimientoProjection;


public interface AlquileresRepository extends JpaRepository<Alquilere, Integer>{
    /**
     * Busca todos los alquileres asociados a un usuario administrador específico.
     *
     * @param administrador El objeto Usuario administrador para el cual se buscarán los alquileres.
     * @return Una lista de objetos Alquilere que corresponden al usuario administrador dado.
     */
	@Query("select a from Alquilere a, Inmueble i where a.inmueble = i.idInmueble AND i.usuarioAdmin = ?1 ")
	public List<Alquilere> buscarAlquileresAdmin(Usuario administrador);
	
	
    /**
     * Busca todos los alquileres asociados a un inmueble específico.
     *
     * @param inmueble El objeto Inmueble para el cual se buscarán los alquileres.
     * @return Una lista de objetos Alquilere que corresponden al inmueble dado.
     */
	@Query("select a from Alquilere a where a.inmueble = ?1 ")
	public List<Alquilere> buscarAlquilerPorInmueble(Inmueble inmueble);
	
	
    /**
     * Busca todos los alquileres asociados a un usuario inquilino específico.
     *
     * @param inquilino El objeto Usuario inquilino para el cual se buscarán los alquileres.
     * @return Una lista de objetos Alquilere que corresponden al usuario inquilino dado.
     */
	@Query("select a from Alquilere a, Inmueble i where a.inmueble = i.idInmueble AND a.usuario = ?1 ")
	public List<Alquilere> buscarAlquilerInquilino(Usuario inquilino);
	
	
    /**
     * Busca todos los alquileres asociados a un usuario propietario específico.
     *
     * @param propietario El objeto Usuario propietario para el cual se buscarán los alquileres.
     * @return Una lista de objetos Alquilere que corresponden al usuario propietario dado.
     */
	@Query("select a from Alquilere a, Inmueble i where a.inmueble = i.idInmueble AND i.usuario = ?1 ")
	public List<Alquilere> buscarTodosPorPropietario(Usuario propietario);	
		
		
	
	
	
	
    /**
     * Busca los vencimientos de los alquileres asociados a un usuario administrador específico.
     *
     * @param administrador El objeto Usuario administrador para el cual se buscarán los vencimientos.
     * @return Una lista de objetos VencimientoProjection que corresponden al usuario administrador dado.
     */
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoProjection(a, DATEDIFF(a.fechaFin, CURDATE())) "
			+ " from Alquilere a, Inmueble i "
			+ " where a.inmueble = i.idInmueble "
			+ " and i.usuarioAdmin = ?1")
	public List<VencimientoProjection> buscarVencimientoAdmin(Usuario administrador);
	
	
    /**
     * Busca los vencimientos de los alquileres asociados a un usuario propietario específico.
     *
     * @param propietario El objeto Usuario propietario para el cual se buscarán los vencimientos.
     * @return Una lista de objetos VencimientoProjection que corresponden al usuario propietario dado.
     */
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoProjection(a, DATEDIFF(a.fechaFin, CURDATE())) from Alquilere a, Inmueble i "
			+ " where a.inmueble = i.idInmueble "
			+ " and i.usuario = ?1")
	public List<VencimientoProjection> buscarVencimientoPropietario(Usuario propietario);
	
	
    /**
     * Busca los vencimientos de los alquileres asociados a un usuario inquilino específico.
     *
     * @param inquilino El objeto Usuario inquilino para el cual se buscarán los vencimientos.
     * @return Una lista de objetos VencimientoProjection que corresponden al usuario inquilino dado.
     */
	@Query("select new com.edix.Proyecto_Final_Curso.modeloDao.VencimientoProjection(a, DATEDIFF(a.fechaFin, CURDATE())) from Alquilere a, Inmueble i "
			+ " where a.inmueble = i.idInmueble "
			+ " and a.usuario = ?1")
	public List<VencimientoProjection> buscarVencimientoInquilino(Usuario inquilino);
}
