package com.edix.Proyecto_Final_Curso.modeloDao;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;

public class VencimientoServiciosProjection {
	
    private AlquilerServicio alquilerServicios;
    private int dias_restantes;
    
	public VencimientoServiciosProjection(AlquilerServicio alquilerServicios, int dias_restantes) {
		super();
		this.alquilerServicios = alquilerServicios;
		this.dias_restantes = dias_restantes;
	}

	public AlquilerServicio getAlquilerServicios() {
		return alquilerServicios;
	}

	public void setAlquilerServicios(AlquilerServicio alquilerServicios) {
		this.alquilerServicios = alquilerServicios;
	}

	public int getDias_restantes() {
		return dias_restantes;
	}

	public void setDias_restantes(int dias_restantes) {
		this.dias_restantes = dias_restantes;
	}

	@Override
	public String toString() {
		String test = (alquilerServicios != null ? alquilerServicios.getNumeroContratoServicio() : null);

		return "VencimientoServiciosProjection [alquilerServicios=" + test  + ", dias_restantes="
				+ dias_restantes + "]";
	}
    

    
}
