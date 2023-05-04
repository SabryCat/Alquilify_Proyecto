package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_contrato database table.
 * 
 */
@Entity
@Table(name="tipo_contrato")
@NamedQuery(name="TipoContrato.findAll", query="SELECT t FROM TipoContrato t")
public class TipoContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_contrato")
	private int idTipoContrato;

	private String descripcion;

	private String tipo;

	//bi-directional many-to-one association to Alquilere
	@OneToMany(mappedBy="tipoContrato")
	private List<Alquilere> alquileres;

	public TipoContrato() {
	}

	public int getIdTipoContrato() {
		return this.idTipoContrato;
	}

	public void setIdTipoContrato(int idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Alquilere> getAlquileres() {
		return this.alquileres;
	}

	public void setAlquileres(List<Alquilere> alquileres) {
		this.alquileres = alquileres;
	}

	public Alquilere addAlquilere(Alquilere alquilere) {
		getAlquileres().add(alquilere);
		alquilere.setTipoContrato(this);

		return alquilere;
	}

	public Alquilere removeAlquilere(Alquilere alquilere) {
		getAlquileres().remove(alquilere);
		alquilere.setTipoContrato(null);

		return alquilere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoContrato;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TipoContrato))
			return false;
		TipoContrato other = (TipoContrato) obj;
		if (idTipoContrato != other.idTipoContrato)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoContrato [idTipoContrato=" + idTipoContrato + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", alquileres=" + alquileres + "]";
	}

}