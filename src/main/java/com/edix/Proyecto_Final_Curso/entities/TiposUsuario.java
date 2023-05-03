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
 * The persistent class for the tipos_usuarios database table.
 * 
 */
@Entity
@Table(name="tipos_usuarios")
@NamedQuery(name="TiposUsuario.findAll", query="SELECT t FROM TiposUsuario t")
public class TiposUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_usuario")
	private int idTipoUsuario;

	private String descripcion;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tiposUsuario")
	private List<Usuario> usuarios;

	public TiposUsuario() {
	}

	public int getIdTipoUsuario() {
		return this.idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTiposUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTiposUsuario(null);

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoUsuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TiposUsuario))
			return false;
		TiposUsuario other = (TiposUsuario) obj;
		if (idTipoUsuario != other.idTipoUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TiposUsuario [idTipoUsuario=" + idTipoUsuario + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", usuarios=" + usuarios + "]";
	}

}