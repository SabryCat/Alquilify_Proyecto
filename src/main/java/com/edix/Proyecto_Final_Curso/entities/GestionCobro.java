package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the gestion_cobros database table.
 * 
 */
@Entity
@Table(name="gestion_cobros")
@NamedQuery(name="GestionCobro.findAll", query="SELECT g FROM GestionCobro g")
public class GestionCobro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cobros")
	private int idCobros;

	@Column(name="estado_cobro")
	private String estadoCobro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_abonado")
	private Date fechaAbonado;

	private float importe;

	private String observaciones;

	private String plazo;

	//bi-directional many-to-one association to AlquilerServicio
	@ManyToOne
	@JoinColumn(name="id_alquiler_servicio")
	private AlquilerServicio alquilerServicio;

	public GestionCobro() {
	}

	public int getIdCobros() {
		return this.idCobros;
	}

	public void setIdCobros(int idCobros) {
		this.idCobros = idCobros;
	}

	public String getEstadoCobro() {
		return this.estadoCobro;
	}

	public void setEstadoCobro(String estadoCobro) {
		this.estadoCobro = estadoCobro;
	}

	public Date getFechaAbonado() {
		return this.fechaAbonado;
	}

	public void setFechaAbonado(Date fechaAbonado) {
		this.fechaAbonado = fechaAbonado;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPlazo() {
		return this.plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public AlquilerServicio getAlquilerServicio() {
		return this.alquilerServicio;
	}

	public void setAlquilerServicio(AlquilerServicio alquilerServicio) {
		this.alquilerServicio = alquilerServicio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCobros;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GestionCobro))
			return false;
		GestionCobro other = (GestionCobro) obj;
		if (idCobros != other.idCobros)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GestionCobro [idCobros=" + idCobros + ", estadoCobro=" + estadoCobro + ", fechaAbonado=" + fechaAbonado
				+ ", importe=" + importe + ", observaciones=" + observaciones + ", plazo=" + plazo
				+ ", alquilerServicio=" + alquilerServicio + "]";
	}
	
}