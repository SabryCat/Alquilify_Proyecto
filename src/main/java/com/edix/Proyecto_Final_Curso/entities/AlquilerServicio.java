package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the alquiler_servicios database table.
 * 
 */
@Entity
@Table(name="alquiler_servicios")
@NamedQuery(name="AlquilerServicio.findAll", query="SELECT a FROM AlquilerServicio a")
public class AlquilerServicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_alquiler_servicio")
	private int idAlquilerServicio;

	private String baja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_contratacion")
	private Date fechaContratacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_finalizacion")
	private Date fechaFinalizacion;

	@Column(name="numero_contrato_servicio")
	private String numeroContratoServicio;

	//bi-directional many-to-one association to Alquilere
	@ManyToOne
	@JoinColumn(name="id_alquiler")
	private Alquilere alquilere;
	
	//uni-directional many-to-one association to ProveedoresServicio
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private ProveedoresServicio proveedoresServicio;
	
	//bi-directional many-to-one association to GestionCobro
	@OneToMany(mappedBy="alquilerServicio")
	private List<GestionCobro> gestionCobros;

	public AlquilerServicio() {
	}

	public int getIdAlquilerServicio() {
		return this.idAlquilerServicio;
	}

	public void setIdAlquilerServicio(int idAlquilerServicio) {
		this.idAlquilerServicio = idAlquilerServicio;
	}

	public String getBaja() {
		return this.baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public Date getFechaContratacion() {
		return this.fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getNumeroContratoServicio() {
		return this.numeroContratoServicio;
	}

	public void setNumeroContratoServicio(String numeroContratoServicio) {
		this.numeroContratoServicio = numeroContratoServicio;
	}

	public Alquilere getAlquilere() {
		return this.alquilere;
	}

	public void setAlquilere(Alquilere alquilere) {
		this.alquilere = alquilere;
	}
	
	public ProveedoresServicio getProveedoresServicio() {
		return this.proveedoresServicio;
	}

	public void setProveedoresServicio(ProveedoresServicio proveedoresServicio) {
		this.proveedoresServicio = proveedoresServicio;
	}

	public List<GestionCobro> getGestionCobros() {
		return this.gestionCobros;
	}

	public void setGestionCobros(List<GestionCobro> gestionCobros) {
		this.gestionCobros = gestionCobros;
	}

	public GestionCobro addGestionCobro(GestionCobro gestionCobro) {
		getGestionCobros().add(gestionCobro);
		gestionCobro.setAlquilerServicio(this);

		return gestionCobro;
	}

	public GestionCobro removeGestionCobro(GestionCobro gestionCobro) {
		getGestionCobros().remove(gestionCobro);
		gestionCobro.setAlquilerServicio(null);

		return gestionCobro;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAlquilerServicio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AlquilerServicio))
			return false;
		AlquilerServicio other = (AlquilerServicio) obj;
		if (idAlquilerServicio != other.idAlquilerServicio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlquilerServicio [idAlquilerServicio=" + idAlquilerServicio + ", baja=" + baja + ", fechaContratacion="
				+ fechaContratacion + ", fechaFinalizacion=" + fechaFinalizacion 
				+ ", numeroContratoServicio=" + numeroContratoServicio + ", proveedoresServicio=" + proveedoresServicio
				+ "]";
	}

}