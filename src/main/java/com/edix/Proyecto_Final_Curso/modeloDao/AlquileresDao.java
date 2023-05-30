package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface AlquileresDao {

	Alquilere buscarAlquiler(int idContrato);
	Alquilere altaAlquiler(Alquilere contrato);
	Alquilere editarAlquiler(Alquilere contrato);
	List<Alquilere> buscarTodosPorAdmin(Usuario administrador);
	List<Alquilere> buscarAlquilerPorInmueble(Inmueble inmueble);
	List<Alquilere> buscarTodosPorInquilino(Usuario inquilino);
	List<Alquilere> buscarTodosPorPropietario(Usuario propietario);
	
}
