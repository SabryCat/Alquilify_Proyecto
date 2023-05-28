package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;
import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.repository.AlquileresServiciosRepository;
@Service
public class AlquileresServiciosDaoImpl implements AlquileresServiciosDao{
	@Autowired
	AlquileresServiciosRepository asrepo;
	
	@Override
	public AlquilerServicio buscarAlquilerServicio(int idAlquilerServicio) {
		return asrepo.findById(idAlquilerServicio).orElse(null);
	}

	@Override
	public AlquilerServicio altaAlquilerServicio(AlquilerServicio alquilerservicio) {
		if(buscarAlquilerServicio(alquilerservicio.getIdAlquilerServicio())==null) {
			try {
				return asrepo.save(alquilerservicio);
			}catch(DataIntegrityViolationException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public List<AlquilerServicio> buscarTodosPorAlquiler(Alquilere alquiler) {
		return asrepo.buscarPorAlquiler(alquiler);
	}

	@Override
	public List<AlquilerServicio> buscarTodosPorAdministrador(Usuario administrador) {
		return asrepo.buscarTodosPorAdministrador(administrador);
	}

	@Override
	public AlquilerServicio buscarServicio(int idServicio) {
		return asrepo.findById(idServicio).orElse(null);
	}

	@Override
	public AlquilerServicio editarAlquilerServicio(AlquilerServicio alquilerservicio) {
		if(buscarAlquilerServicio(alquilerservicio.getIdAlquilerServicio())!=null) {
			return asrepo.save(alquilerservicio);
		}
		return null;
	}

	
}
