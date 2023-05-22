package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.EntidadesServicio;

public interface Entidades_servicioDao {
	
	List<EntidadesServicio> buscarTodos();
	EntidadesServicio buscarEntidadServicio(int idEntidadServicio);
}
