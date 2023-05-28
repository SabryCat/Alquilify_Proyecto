package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;
import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface AlquileresServiciosDao {
	
	AlquilerServicio buscarServicio(int idServicio);
	AlquilerServicio editarAlquilerServicio(AlquilerServicio alquilerservicio);
	AlquilerServicio buscarAlquilerServicio(int idAlquilerServicio);
	AlquilerServicio altaAlquilerServicio(AlquilerServicio alquilerservicio);
	List<AlquilerServicio> buscarTodosPorAlquiler(Alquilere alquiler);
	List<AlquilerServicio> buscarTodosPorAdministrador(Usuario administrador);
	
}
