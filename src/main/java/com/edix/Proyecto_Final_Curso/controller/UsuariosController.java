package com.edix.Proyecto_Final_Curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/modulo")
	public String home(Model model) {		
		List<TiposUsuario> tipousuario = tudao.buscarTodos(); 
		model.addAttribute("tipousuario",tipousuario);
		List<Usuario> propietarios = udao.buscarTodosPropietarios();
		model.addAttribute("propietarios",propietarios);
		List<Usuario> inquilinos = udao.buscarTodosInquilinos();
		model.addAttribute("inquilinos",inquilinos);
		return "app/usuarios";		 		
	}
	
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
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");
			return "redirect:/cuenta";		
		}else {
			return "redirect:/app/panelControl";
		}
	}
	
	@PostMapping("/altaNuevosUsuarios")
	public String altaNuevosUsuarios(@RequestParam String tipoDeUsuario,
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
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");		
		}else {
			redirect.addFlashAttribute("info", "Usuario dado de alta correctamente");
		}
		return "redirect:/usuarios/modulo";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		udao.eliminarUsuario(idUsuario);
		redirect.addFlashAttribute("info", "Usuario se ha eliminado correctamente");
		return "redirect:/usuarios/modulo";
	}
	
	@GetMapping("/verficha/{id}")
	public String verFichaUsuario(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		System.out.println("ENTRO");
		Usuario usuario = udao.buscarUsuario(idUsuario);
		System.out.println(usuario);
		if(usuario == null) {
			redirect.addFlashAttribute("info", "El usuario no existe y no se puede modificar");
			return "redirect:/usuarios/modulo";
		}
		System.out.println("ENVIO");
		model.addAttribute("usuario", usuario);
		return "app/usuariosFicha";	
	}
	
}
