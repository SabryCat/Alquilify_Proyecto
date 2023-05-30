package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.repository.AlquileresRepository;

@Service
public class AlquileresDaoImpl implements AlquileresDao {
	@Autowired
	AlquileresRepository arepo;
	
	@Override
	public Alquilere buscarAlquiler(int idContrato) {
		return arepo.findById(idContrato).orElse(null);
	}
	
	@Override
	public Alquilere altaAlquiler(Alquilere alquiler) {
		if(buscarAlquiler(alquiler.getIdAlquiler())==null) {
			try {
				return arepo.save(alquiler);
			}catch(DataIntegrityViolationException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public List<Alquilere> buscarTodosPorAdmin(Usuario administrador) {
		return arepo.buscarAlquileresAdmin(administrador);
	}

	@Override
	public Alquilere editarAlquiler(Alquilere contrato) {
		if(buscarAlquiler(contrato.getIdAlquiler())!=null) {
			return arepo.save(contrato);
		}
		return null;
	}

	@Override
	public List<Alquilere> buscarAlquilerPorInmueble(Inmueble inmueble) {
		return arepo.buscarAlquilerPorInmueble(inmueble);
	}
	
	@Override
	public List<Alquilere> buscarTodosPorInquilino(Usuario inquilino) {
		return arepo.buscarAlquilerInquilino(inquilino);
	}

	@Override
	public List<Alquilere> buscarTodosPorPropietario(Usuario propietario) {
		return arepo.buscarTodosPorPropietario(propietario);
	}
	
}
