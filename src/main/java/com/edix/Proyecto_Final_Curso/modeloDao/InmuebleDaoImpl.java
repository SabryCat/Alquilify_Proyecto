package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.repository.InmuebleRepository;

@Service
public class InmuebleDaoImpl implements InmuebleDao {
	@Autowired
	InmuebleRepository irepo;
	
	@Override
	public Inmueble buscarInmueble(int idInmueble) {
		return irepo.findById(idInmueble).orElse(null);
	}

	@Override
	public Inmueble altaInmueble(Inmueble inmueble) {
		if(buscarInmueble(inmueble.getIdInmueble())==null) {
			try {
				return irepo.save(inmueble);
			}catch(DataIntegrityViolationException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public Inmueble editarInmueble(Inmueble inmueble) {
		if(buscarInmueble(inmueble.getIdInmueble())!=null) {
			return irepo.save(inmueble);
		}
		return null;
	}

	@Override
	public List<Inmueble> buscarTodosPorAdmin(Usuario administrador) {
		return irepo.buscarInmueblesAdmin(administrador);
	}

	@Override
	public void eliminarInmueble(int idInmueble) {
		if(buscarInmueble(idInmueble)!=null) {
			irepo.deleteById(idInmueble);
		}
		
	}

	@Override
	public List<Inmueble> buscarTodosPropietario(Usuario propietario) {
		return irepo.buscarInmueblesPropietario(propietario);
	}

}
