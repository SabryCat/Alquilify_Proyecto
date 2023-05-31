package com.edix.Proyecto_Final_Curso.modeloDao;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;

public class VencimientoProjection {
    private Alquilere alquilere;
    private int dias_restantes;
    
	public VencimientoProjection(Alquilere alquilere, int dias_restantes) {
		super();
		this.alquilere = alquilere;
		this.dias_restantes = dias_restantes;
	}

	public Alquilere getAlquilere() {
		return alquilere;
	}

	public void setAlquilere(Alquilere alquilere) {
		this.alquilere = alquilere;
	}

	public long getDias_restantes() {
		return dias_restantes;
	}

	public void setDias_restantes(int dias_restantes) {
		this.dias_restantes = dias_restantes;
	}
    
}
