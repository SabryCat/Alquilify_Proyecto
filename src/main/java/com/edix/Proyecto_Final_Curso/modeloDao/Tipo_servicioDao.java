package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.TiposServicio;

public interface Tipo_servicioDao {

	List<TiposServicio> buscarTodos();
	TiposServicio buscarTipoServicio(int idTipoServicio);
}
