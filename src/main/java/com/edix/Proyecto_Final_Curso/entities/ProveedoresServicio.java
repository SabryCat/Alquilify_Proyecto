package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proveedores_servicios database table.
 * 
 */
@Entity
@Table(name="proveedores_servicios")
@NamedQuery(name="ProveedoresServicio.findAll", query="SELECT p FROM ProveedoresServicio p")
public class ProveedoresServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private int idProveedor;

	private String nombre;

	//uni-directional many-to-one association to TiposServicio
	@ManyToOne
	@JoinColumn(name="id_tipo_servicio")
	private TiposServicio tiposServicio;

	public ProveedoresServicio() {
	}

	public ProveedoresServicio(int idProveedor, String nombre, TiposServicio tiposServicio) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.tiposServicio = tiposServicio;
	}

	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TiposServicio getTiposServicio() {
		return this.tiposServicio;
	}

	public void setTiposServicio(TiposServicio tiposServicio) {
		this.tiposServicio = tiposServicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProveedor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ProveedoresServicio))
			return false;
		ProveedoresServicio other = (ProveedoresServicio) obj;
		if (idProveedor != other.idProveedor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProveedoresServicio [idProveedor=" + idProveedor + ", nombre=" + nombre + ", tiposServicio="
				+ tiposServicio + "]";
	}

}