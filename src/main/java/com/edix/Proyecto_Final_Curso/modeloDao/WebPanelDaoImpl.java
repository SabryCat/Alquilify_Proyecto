package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.repository.AlquileresRepository;
import com.edix.Proyecto_Final_Curso.repository.AlquileresServiciosRepository;

@Service
public class WebPanelDaoImpl implements WebPanelDao {
	@Autowired
	AlquileresRepository arepo;	
	@Autowired	
	AlquileresServiciosRepository asrepo;
	
	@Override
	public List<VencimientoProjection> buscarFechasVencimiento(Usuario administrador) {
		return arepo.buscarVencimientoAdmin(administrador);

	}
	@Override
	public List<VencimientoServiciosProjection> buscarFechasVencimientoServicios(Usuario administrador) {
		return asrepo.buscarVencimientoServiciosAdmin(administrador);
	}
	
	
	@Override
	public List<VencimientoProjection> buscarVencimientoPropietario(Usuario propietario) {
		return arepo.buscarVencimientoPropietario(propietario);		
	}
	@Override
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosProp(Usuario propietario) {
		return asrepo.buscarVencimientoServiciosProp(propietario);
	}
	
	
	@Override
	public List<VencimientoProjection> buscarVencimientoInqui(Usuario inquilino) {
		return arepo.buscarVencimientoInquilino(inquilino);	
	}
	@Override
	public List<VencimientoServiciosProjection> buscarVencimientoServiciosInqui(Usuario inquilino) {
		return asrepo.buscarVencimientoServiciosInqui(inquilino);
	}
}
