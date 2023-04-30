package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


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

	//bi-directional many-to-one association to AlquilerServicio
	@OneToMany(mappedBy="tiposServicio")
	private List<AlquilerServicio> alquilerServicios;

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

	public List<AlquilerServicio> getAlquilerServicios() {
		return this.alquilerServicios;
	}

	public void setAlquilerServicios(List<AlquilerServicio> alquilerServicios) {
		this.alquilerServicios = alquilerServicios;
	}

	public AlquilerServicio addAlquilerServicio(AlquilerServicio alquilerServicio) {
		getAlquilerServicios().add(alquilerServicio);
		alquilerServicio.setTiposServicio(this);

		return alquilerServicio;
	}

	public AlquilerServicio removeAlquilerServicio(AlquilerServicio alquilerServicio) {
		getAlquilerServicios().remove(alquilerServicio);
		alquilerServicio.setTiposServicio(null);

		return alquilerServicio;
	}

}