package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;


import com.edix.Proyecto_Final_Curso.entities.Usuario;

public interface WebPanelDao {
	List<VencimientoProjection> buscarFechasVencimiento(Usuario administrador);
	List<VencimientoServiciosProjection> buscarFechasVencimientoServicios(Usuario administrador);
	
	List<VencimientoProjection> buscarVencimientoPropietario(Usuario propietario);
	List<VencimientoServiciosProjection> buscarVencimientoServiciosProp(Usuario propietario);
	
	List<VencimientoProjection> buscarVencimientoInqui(Usuario inquilino);
	List<VencimientoServiciosProjection> buscarVencimientoServiciosInqui(Usuario inquilino);	
}
