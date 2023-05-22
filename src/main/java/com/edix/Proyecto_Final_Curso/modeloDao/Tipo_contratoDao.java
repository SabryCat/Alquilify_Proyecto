package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.TipoContrato;

public interface Tipo_contratoDao {
	
	List<TipoContrato> buscarTodos();
	TipoContrato buscarTipoContrato(int idTipoContrato);

}
