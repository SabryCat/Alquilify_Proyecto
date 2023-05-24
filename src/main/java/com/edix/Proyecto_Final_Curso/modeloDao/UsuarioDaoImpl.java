package com.edix.Proyecto_Final_Curso.modeloDao;
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

}
