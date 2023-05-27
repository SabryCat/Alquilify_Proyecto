package com.edix.Proyecto_Final_Curso.entities;

import java.util.Date;

public class Fecha {
	
	private Date fecha;

	public Fecha(Date fecha) {
		super();
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Fecha))
			return false;
		Fecha other = (Fecha) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fecha [fecha=" + fecha + "]";
	}
	
	
}
