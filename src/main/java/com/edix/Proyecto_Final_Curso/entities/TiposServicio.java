package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipos_servicio database table.
 * 
 */
@Entity
@Table(name="tipos_servicio")
@NamedQuery(name="TiposServicio.findAll", query="SELECT t FROM TiposServicio t")
public class TiposServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_servicio")
	private int idTipoServicio;

	private String decripcion;

	private String tipo;

	public TiposServicio() {
	}

	public int getIdTipoServicio() {
		return this.idTipoServicio;
	}

	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public String getDecripcion() {
		return this.decripcion;
	}

	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoServicio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TiposServicio))
			return false;
		TiposServicio other = (TiposServicio) obj;
		if (idTipoServicio != other.idTipoServicio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TiposServicio [idTipoServicio=" + idTipoServicio + ", decripcion=" + decripcion + ", tipo=" + tipo
				+ "]";
	}

}