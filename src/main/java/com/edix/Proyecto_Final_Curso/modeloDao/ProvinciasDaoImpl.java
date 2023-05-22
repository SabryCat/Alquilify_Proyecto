package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.Provincia;
import com.edix.Proyecto_Final_Curso.repository.ProvinciasRepository;
@Repository
public class ProvinciasDaoImpl implements ProvinciasDao {
	@Autowired
	private ProvinciasRepository prepo;
	
	@Override
	public List<Provincia> buscarTodas() {
		return prepo.findAll();
	}

	@Override
	public Provincia buscarProvincia(int idProvincia) {
		return prepo.findById(idProvincia).orElse(null);
	}

}
