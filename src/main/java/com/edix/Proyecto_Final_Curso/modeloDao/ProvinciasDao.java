package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Provincia;

public interface ProvinciasDao {

	List<Provincia> buscarTodas();
	Provincia buscarProvincia(int idProvincia);
;}
