package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.Proyecto_Final_Curso.entities.ProveedoresServicio;
import com.edix.Proyecto_Final_Curso.repository.Proveedores_servicioRepository;
@Repository
public class Proveedores_servicioDaoImpl implements Proveedores_servicioDao{
	@Autowired
	private Proveedores_servicioRepository esrepo;
	@Override
	public List<ProveedoresServicio> buscarTodos() {
		return esrepo.findAll();
	}

	@Override
	public ProveedoresServicio buscarEntidadServicio(int idProveedor) {
		return esrepo.findById(idProveedor).orElse(null);
	}

}
