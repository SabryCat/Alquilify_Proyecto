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

import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private String apellidos;

	private String clave;

	private String domicilio;

	private String email;

	private String nif;

	private String nombre;

	private int permitido;

	private String telefono;

	//bi-directional many-to-one association to Alquilere
	@OneToMany(mappedBy="usuario")
	private List<Alquilere> alquileres;

	//bi-directional many-to-one association to Inmueble
	@OneToMany(mappedBy="usuario")
	private List<Inmueble> inmuebles;

	//bi-directional many-to-one association to TiposUsuario
	@ManyToOne
	@JoinColumn(name="id_tipo_usuario")
	private TiposUsuario tiposUsuario;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPermitido() {
		return this.permitido;
	}

	public void setPermitido(int permitido) {
		this.permitido = permitido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Alquilere> getAlquileres() {
		return this.alquileres;
	}

	public void setAlquileres(List<Alquilere> alquileres) {
		this.alquileres = alquileres;
	}

	public Alquilere addAlquilere(Alquilere alquilere) {
		getAlquileres().add(alquilere);
		alquilere.setUsuario(this);

		return alquilere;
	}

	public Alquilere removeAlquilere(Alquilere alquilere) {
		getAlquileres().remove(alquilere);
		alquilere.setUsuario(null);

		return alquilere;
	}

	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Inmueble addInmueble(Inmueble inmueble) {
		getInmuebles().add(inmueble);
		inmueble.setUsuario(this);

		return inmueble;
	}

	public Inmueble removeInmueble(Inmueble inmueble) {
		getInmuebles().remove(inmueble);
		inmueble.setUsuario(null);

		return inmueble;
	}

	public TiposUsuario getTiposUsuario() {
		return this.tiposUsuario;
	}

	public void setTiposUsuario(TiposUsuario tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellidos=" + apellidos + ", clave=" + clave + ", domicilio="
				+ domicilio + ", email=" + email + ", nif=" + nif + ", nombre=" + nombre + ", permitido=" + permitido
				+ ", telefono=" + telefono + ", alquileres=" + alquileres + ", inmuebles=" + inmuebles
				+ ", tiposUsuario=" + tiposUsuario + "]";
	}

}