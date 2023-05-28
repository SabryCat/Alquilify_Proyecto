package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface AlquileresDao {

	Alquilere buscarAlquiler(int idContrato);
	Alquilere altaAlquiler(Alquilere contrato);
	List<Alquilere> buscarTodosPorAdmin(Usuario administrador);
}
