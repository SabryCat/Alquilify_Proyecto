package com.edix.Proyecto_Final_Curso.modeloDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.repository.UsuarioRepository;

@Service
public class UsuarioDaoImpl implements UsuarioDao {
	@Autowired
	UsuarioRepository urepo;
	
	
	@Override
	public Usuario altaUsuario(Usuario usuario) {
		if(buscarUsuario(usuario.getIdUsuario())==null) {
			try {
				return urepo.save(usuario);
			}catch(DataIntegrityViolationException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public Usuario buscarUsuario(int idUsuario) {
		return urepo.findById(idUsuario).orElse(null);
	}

	@Override
	public List<Usuario> buscarTodosPropietarios() {
		return urepo.buscarPropietarios();
	}

	@Override
	public List<Usuario> buscarTodosInquilinos() {
		return urepo.buscarInquilinos();
	}

	@Override
	public void eliminarUsuario(int idUsuario) {
		if(buscarUsuario(idUsuario)!=null) {
			urepo.deleteById(idUsuario);
		}		
	}

}
