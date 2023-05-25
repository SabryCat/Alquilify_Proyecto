package com.edix.Proyecto_Final_Curso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inmuebles database table.
 * 
 */
@Entity
@Table(name="inmuebles")
@NamedQuery(name="Inmueble.findAll", query="SELECT i FROM Inmueble i")
public class Inmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inmueble")
	private int idInmueble;

	@Column(name="anio_contruccion")
	private String anioContruccion;

	private String ascensor;

	private int banios;

	private String direccion;

	private int habitaciones;

	private float metros;

	@Column(name="n_catastral")
	private String nCatastral;

	private String observaciones;

	@Column(name="plaza_garage")
	private String plazaGarage;

	@Column(name="tipo_finca")
	private String tipoFinca;

	@Column(name="zona_exterior")
	private String zonaExterior;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_propietario")
	private Usuario usuario;
	
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_administrador")
	private Usuario usuario_admin;
	

	public Inmueble() {
	}

	public int getIdInmueble() {
		return this.idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}

	public String getAnioContruccion() {
		return this.anioContruccion;
	}

	public void setAnioContruccion(String anioContruccion) {
		this.anioContruccion = anioContruccion;
	}

	public String getAscensor() {
		return this.ascensor;
	}

	public void setAscensor(String ascensor) {
		this.ascensor = ascensor;
	}

	public int getBanios() {
		return this.banios;
	}

	public void setBanios(int banios) {
		this.banios = banios;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public float getMetros() {
		return this.metros;
	}

	public void setMetros(float metros) {
		this.metros = metros;
	}

	public String getNCatastral() {
		return this.nCatastral;
	}

	public void setNCatastral(String nCatastral) {
		this.nCatastral = nCatastral;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPlazaGarage() {
		return this.plazaGarage;
	}

	public void setPlazaGarage(String plazaGarage) {
		this.plazaGarage = plazaGarage;
	}

	public String getTipoFinca() {
		return this.tipoFinca;
	}

	public void setTipoFinca(String tipoFinca) {
		this.tipoFinca = tipoFinca;
	}

	public String getZonaExterior() {
		return this.zonaExterior;
	}

	public void setZonaExterior(String zonaExterior) {
		this.zonaExterior = zonaExterior;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario_admin() {
		return usuario_admin;
	}

	public void setUsuario_admin(Usuario usuario_admin) {
		this.usuario_admin = usuario_admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idInmueble;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Inmueble))
			return false;
		Inmueble other = (Inmueble) obj;
		if (idInmueble != other.idInmueble)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inmueble [idInmueble=" + idInmueble + ", anioContruccion=" + anioContruccion + ", ascensor=" + ascensor
				+ ", banios=" + banios + ", direccion=" + direccion + ", habitaciones=" + habitaciones + ", metros="
				+ metros + ", nCatastral=" + nCatastral + ", observaciones=" + observaciones + ", plazaGarage="
				+ plazaGarage + ", tipoFinca=" + tipoFinca + ", zonaExterior=" + zonaExterior + ", provincia="
				+ provincia + ", usuario=" + usuario + ", usuario_admin=" + usuario_admin + "]";
	}



}