package com.edix.Proyecto_Final_Curso.modeloDao;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;

public interface AlquileresDao {

	Alquilere buscarAlquiler(int idContrato);
	Alquilere altaAlquiler(Alquilere contrato);
}
