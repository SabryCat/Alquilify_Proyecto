package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the alquileres database table.
 * 
 */
@Entity
@Table(name="alquileres")
@NamedQuery(name="Alquilere.findAll", query="SELECT a FROM Alquilere a")
public class Alquilere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_alquiler")
	private int idAlquiler;

	@Column(name="actualizacion_renta")
	private double actualizacionRenta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_comienzo")
	private Date fechaComienzo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	private int fianza;

	private float importe;

	@Column(name="importe_fianza")
	private float importeFianza;

	private String observaciones;

	private String renovacion;

	//bi-directional many-to-one association to AlquilerServicio
	@OneToMany(mappedBy="alquilere")
	private List<AlquilerServicio> alquilerServicios;

	//bi-directional many-to-one association to TipoContrato
	@ManyToOne
	@JoinColumn(name="id_tipo_contrato")
	private TipoContrato tipoContrato;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_inquilino")
	private Usuario usuario;

	public Alquilere() {
	}

	public int getIdAlquiler() {
		return this.idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public double getActualizacionRenta() {
		return this.actualizacionRenta;
	}

	public void setActualizacionRenta(double actualizacionRenta) {
		this.actualizacionRenta = actualizacionRenta;
	}

	public Date getFechaComienzo() {
		return this.fechaComienzo;
	}

	public void setFechaComienzo(Date fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getFianza() {
		return this.fianza;
	}

	public void setFianza(int fianza) {
		this.fianza = fianza;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public float getImporteFianza() {
		return this.importeFianza;
	}

	public void setImporteFianza(float importeFianza) {
		this.importeFianza = importeFianza;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getRenovacion() {
		return this.renovacion;
	}

	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	public List<AlquilerServicio> getAlquilerServicios() {
		return this.alquilerServicios;
	}

	public void setAlquilerServicios(List<AlquilerServicio> alquilerServicios) {
		this.alquilerServicios = alquilerServicios;
	}

	public AlquilerServicio addAlquilerServicio(AlquilerServicio alquilerServicio) {
		getAlquilerServicios().add(alquilerServicio);
		alquilerServicio.setAlquilere(this);

		return alquilerServicio;
	}

	public AlquilerServicio removeAlquilerServicio(AlquilerServicio alquilerServicio) {
		getAlquilerServicios().remove(alquilerServicio);
		alquilerServicio.setAlquilere(null);

		return alquilerServicio;
	}

	public TipoContrato getTipoContrato() {
		return this.tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAlquiler;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alquilere))
			return false;
		Alquilere other = (Alquilere) obj;
		if (idAlquiler != other.idAlquiler)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alquilere [idAlquiler=" + idAlquiler + ", actualizacionRenta=" + actualizacionRenta + ", fechaComienzo="
				+ fechaComienzo + ", fechaFin=" + fechaFin + ", fianza=" + fianza + ", importe=" + importe
				+ ", importeFianza=" + importeFianza + ", observaciones=" + observaciones + ", renovacion=" + renovacion
				+ ", alquilerServicios=" + alquilerServicios + ", tipoContrato=" + tipoContrato + ", usuario=" + usuario
				+ "]";
	}

}