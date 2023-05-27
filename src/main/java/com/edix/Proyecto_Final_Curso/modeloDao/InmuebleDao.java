package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface InmuebleDao {
	
	Inmueble buscarInmueble(int idInmueble);
	Inmueble altaInmueble(Inmueble inmueble);
	Inmueble editarInmueble(Inmueble inmuebles);
	List<Inmueble> buscarTodosPorAdmin(Usuario administrador);
	List<Inmueble> buscarTodosPropietario(Usuario propietario);
	void eliminarInmueble(int idInmueble);
}
