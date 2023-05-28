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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresDao;
import com.edix.Proyecto_Final_Curso.modeloDao.InmuebleDao;
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
	@Autowired
	private InmuebleDao idao;
	@Autowired
	private AlquileresDao adao;
	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idAdmin, Model model) {
		List<TiposUsuario> tipousuario = tudao.buscarTodos(); 
		model.addAttribute("tipousuario",tipousuario);
		List<Usuario> propietarios = udao.buscarTodosPropietarios(idAdmin);
		model.addAttribute("propietarios",propietarios);
		List<Usuario> inquilinos = udao.buscarTodosInquilinos(idAdmin);
		model.addAttribute("inquilinos",inquilinos);
		return "app/usuarios";		 		
	}
		
	@GetMapping("/verPropiedades/{id}")
	public String inmueblesPropietario(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		Usuario usuario = udao.buscarUsuario(idUsuario);
		List<Inmueble> inmuebles = idao.buscarTodosPropietario(usuario);
		if(inmuebles.size() == 0) {
			redirect.addFlashAttribute("info", "Este usuario actualmente no tiene propiedades");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}else {
			model.addAttribute("inmuebles", inmuebles);
			model.addAttribute("usuario", usuario);
			return "app/inmueblesPropietario";
		}		
	}
	
	@GetMapping("/verAlquileres/{id}")
	public String contratosInquilino(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		Usuario inquilino = udao.buscarUsuario(idUsuario);
		List<Alquilere> contratos = adao.buscarTodosPorInquilino(inquilino);
		if(contratos.size() == 0) {
			redirect.addFlashAttribute("info", "Este usuario actualmente no tiene contratos de alquiler");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}else {
			model.addAttribute("contratos",contratos);
			return "app/alquileresInquilino";
		}	
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
		Usuario usuario = new Usuario(0, apellidos, encriptado, domicilio, email, nif, nombre, 1, telefono, 0, rolUsuario);
		if(udao.altaUsuario(usuario)==null) {
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/cuenta";		
		}else {
			return "redirect:/app/panelControl";
		}
	}
	
	@PostMapping("/altaNuevosUsuarios")
	public String altaNuevosUsuarios(@SessionAttribute("idUsuarioSession") int idAdmin,
										@RequestParam String tipoDeUsuario,
										@RequestParam String nombre,
										@RequestParam String apellidos,
										@RequestParam String nif,
										@RequestParam String email,
										@RequestParam String telefono,
										@RequestParam String domicilio,
										@RequestParam String clave,
										Model model, RedirectAttributes redirect) {
	
		String encriptado = passwordEncoder.encode(clave); 
		TiposUsuario tipUsuario = tudao.buscarTipoUsuario(Integer.parseInt(tipoDeUsuario));
		Usuario usuario = new Usuario(0, apellidos, encriptado, domicilio, email, nif, nombre, 1, telefono, idAdmin, tipUsuario);
		if(udao.altaUsuario(usuario)==null) {
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			redirect.addFlashAttribute("info", "Usuario dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/usuarios/modulo";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		Usuario usuario = udao.buscarUsuario(idUsuario);
		List<Inmueble> inmuebles = idao.buscarTodosPropietario(usuario);
		if(inmuebles.size() == 0) {
			udao.eliminarUsuario(idUsuario);
			redirect.addFlashAttribute("info", "Usuario se ha eliminado correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}else {
			redirect.addFlashAttribute("info", "Este usuario tiene propiedades activas, asegurese de eliminar todas sus propiedades primero.");
			redirect.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/usuarios/modulo";
	}
	
	@GetMapping("/verficha/{id}")
	public String verFichaUsuario(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		Usuario usuario = udao.buscarUsuario(idUsuario);
		if(usuario == null) {
			redirect.addFlashAttribute("info", "El usuario no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}
		model.addAttribute("usuario", usuario);
		return "app/usuariosFicha";	
	}
	
	@PostMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") int idUsuario,
								@RequestParam String nombre,
								@RequestParam String apellidos,
								@RequestParam String nif,
								@RequestParam String email,
								@RequestParam String telefono,
								@RequestParam String domicilio,
								Model model, RedirectAttributes redirect) {
		Usuario usuario = udao.buscarUsuario(idUsuario);
		if(usuario==null) {
			redirect.addFlashAttribute("info", "El usuario no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setNif(nif);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setDomicilio(domicilio);
		udao.editarUsuario(usuario);
		redirect.addFlashAttribute("info", "Usuario modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		return "redirect:/usuarios/modulo";		
	}
}
