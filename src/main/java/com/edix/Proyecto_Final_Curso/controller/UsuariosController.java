package com.edix.Proyecto_Final_Curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_usuarioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;

@RequestMapping("/usuarios")
@Controller
public class UsuariosController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Tipo_usuarioDao tudao;
	@Autowired
	private UsuarioDao udao;
	
	@PostMapping("/altaUsuario")
	public String altaUsuario(@RequestParam String tipoDeUsuario,
								@RequestParam String nombre,
								@RequestParam String apellidos,
								@RequestParam String nif,
								@RequestParam String email,
								@RequestParam String telefono,
								@RequestParam String domicilio,
								@RequestParam String clave,
								Model model, RedirectAttributes redirect) {
	
		String encriptado = passwordEncoder.encode(clave); 
		int tipUsuario = Integer.parseInt(tipoDeUsuario);
		TiposUsuario rolUsuario = tudao.buscarTipoUsuario(tipUsuario);
		Usuario usuario = new Usuario(0, apellidos, encriptado, domicilio, email, nif, nombre, 1, telefono, rolUsuario);
		if(udao.altaUsuario(usuario)==null) {
			redirect.addFlashAttribute("info", "El usuario ya existe en nuetra plataforma");
			return "redirect:/cuenta";		
		}else {
			redirect.addFlashAttribute("info", "El usuario se ha insertado correctamente");
			return "redirect:/cuenta";
		}
	}
}
