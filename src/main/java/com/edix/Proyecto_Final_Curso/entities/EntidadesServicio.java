package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entidades_servicios database table.
 * 
 */
@Entity
@Table(name="entidades_servicios")
@NamedQuery(name="EntidadesServicio.findAll", query="SELECT e FROM EntidadesServicio e")
public class EntidadesServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_entidad_servicio")
	private int idEntidadServicio;

	private String nombre;

	private String tipo;

	//bi-directional many-to-one association to AlquilerServicio
	@OneToMany(mappedBy="entidadesServicio")
	private List<AlquilerServicio> alquilerServicios;

	public EntidadesServicio() {
	}

	public int getIdEntidadServicio() {
		return this.idEntidadServicio;
	}

	public void setIdEntidadServicio(int idEntidadServicio) {
		this.idEntidadServicio = idEntidadServicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		alquilerServicio.setEntidadesServicio(this);

		return alquilerServicio;
	}

	public AlquilerServicio removeAlquilerServicio(AlquilerServicio alquilerServicio) {
		getAlquilerServicios().remove(alquilerServicio);
		alquilerServicio.setEntidadesServicio(null);

		return alquilerServicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEntidadServicio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EntidadesServicio))
			return false;
		EntidadesServicio other = (EntidadesServicio) obj;
		if (idEntidadServicio != other.idEntidadServicio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntidadesServicio [idEntidadServicio=" + idEntidadServicio + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", alquilerServicios=" + alquilerServicios + "]";
	}

}