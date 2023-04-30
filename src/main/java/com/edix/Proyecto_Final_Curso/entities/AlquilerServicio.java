package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	//bi-directional many-to-one association to EntidadesServicio
	@ManyToOne
	@JoinColumn(name="id_emisor")
	private EntidadesServicio entidadesServicio;

	//bi-directional many-to-one association to TiposServicio
	@ManyToOne
	@JoinColumn(name="id_tipo_servicio")
	private TiposServicio tiposServicio;

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

	public EntidadesServicio getEntidadesServicio() {
		return this.entidadesServicio;
	}

	public void setEntidadesServicio(EntidadesServicio entidadesServicio) {
		this.entidadesServicio = entidadesServicio;
	}

	public TiposServicio getTiposServicio() {
		return this.tiposServicio;
	}

	public void setTiposServicio(TiposServicio tiposServicio) {
		this.tiposServicio = tiposServicio;
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

}