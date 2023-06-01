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

/**
 * Controlador para crud de usuario
 * 
 * El objetivo es llevar a cabo las operariones crud
 * y cargar listados relacionados con el usuario
 * 
 * @author sabrycat
 * @version 1.0
 */
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
	
	/**
	 * 
	 * Solo los administradores y los propietarios pueden ver esta vista
	 * 
	 * @param idUsuario obtenido de la session
	 * @param tipoUsuarioSession obtenido de la session
	 * @param model
	 * @return  app/usuarios vista principal del modulo usuarios con listados necesarios
	 */	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idUsuario, 
						@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession, Model model) {
		
		/**
		 * Comparar tipo de usuario para cargar listados
		 */	
		if(tipoUsuarioSession.equals("Administrador")) {
			/**
			 * Buscar propietaros, inquilinos 
			 * y listado de tipos de usuario para el formulario
			 * alta de nuevos usuarios
			 */	
			List<TiposUsuario> tipousuario = tudao.buscarTodos(); 
			model.addAttribute("tipousuario",tipousuario);
			List<Usuario> propietarios = udao.buscarTodosPropietarios(idUsuario);
			model.addAttribute("propietarios",propietarios);
			List<Usuario> inquilinos = udao.buscarTodosInquilinos(idUsuario);
			model.addAttribute("inquilinos",inquilinos);
		}
		if(tipoUsuarioSession.equals("Propietario")) {
			/**
			 * Buscar inquilinos segun propietario
			 */	
			Usuario propietario = udao.buscarUsuario(idUsuario);
			List<Usuario> inquilinos = udao.buscarInquilinosPorPropietario(propietario);
			model.addAttribute("inquilinos",inquilinos);
		}

		return "app/usuarios";		 		
	}
	

	/**
	 * 
	 * Listar todos los inmuebles de un mismo propietario
	 * 
	 * @param idPropietario
	 * @param model
	 * @param redirect
	 * @return app/inmueblesPropietario
	 */
	@GetMapping("/verPropiedades/{id}")
	public String inmueblesPropietario(@PathVariable("id") int idPropietario, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto usuario propietario
		 */
		Usuario propietario = udao.buscarUsuario(idPropietario);
		/**
		 * Listar inmuebles segun propietario
		 */
		List<Inmueble> inmuebles = idao.buscarTodosPropietario(propietario);
		/**
		 * Si es igual a cero 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(inmuebles.size() == 0) {
			redirect.addFlashAttribute("info", "Este propietario actualmente no tiene propiedades");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}else {
			/**
			 * Si no
			 * Redireccionar a la vista de inmueblesPropietario
			 * con los inmuebles y datos del propietario
			 */
			model.addAttribute("inmuebles", inmuebles);
			model.addAttribute("usuario", propietario);
			return "app/inmueblesPropietario";
		}		
	}
	
	/**
	 * 
	 * * Listar todos los contratos de alquiler de un mismo inquilino
	 * 
	 * @param idInquilino
	 * @param model
	 * @param redirect
	 * @return app/alquileresInquilino
	 */
	@GetMapping("/verAlquileres/{id}")
	public String contratosInquilino(@PathVariable("id") int idInquilino, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto usuario inquilino
		 */
		Usuario inquilino = udao.buscarUsuario(idInquilino);
		/**
		 * Listar Alquileres segun inquilino
		 */
		List<Alquilere> contratos = adao.buscarTodosPorInquilino(inquilino);
		/**
		 * Si es igual a cero 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(contratos.size() == 0) {
			redirect.addFlashAttribute("info", "Este usuario actualmente no tiene contratos de alquiler");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}else {
			/**
			 * Si no
			 * Redireccionar a la vista de alquileresInquilino
			 * con los contratos de alquiler
			 */
			model.addAttribute("contratos",contratos);
			return "app/alquileresInquilino";
		}	
	}
	
	/**
	 * 
	 * Formulario alta para usuarios desde el sitio web
	 * 
	 * @param tipoDeUsuario
	 * @param nombre
	 * @param apellidos
	 * @param nif
	 * @param email
	 * @param telefono
	 * @param domicilio
	 * @param clave
	 * @param model
	 * @param redirect
	 * @return redirect:/cuenta o /app/panelControl
	 */
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
		/**
		 * Encriptar clave
		 */
		String encriptado = passwordEncoder.encode(clave); 
		/**
		 * Obtener objeto rolUsuario parseando el recibido del formulario
		 */
		TiposUsuario rolUsuario = tudao.buscarTipoUsuario(Integer.parseInt(tipoDeUsuario));
		/**
		 * Cargar nuevo objeto del tipo usuario
		 */
		Usuario usuario = new Usuario(0, apellidos, encriptado, domicilio, email, nif, nombre, 1, telefono, 0, rolUsuario);
		/**
		 * Si el alta devuelve null ya existe
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(udao.altaUsuario(usuario)==null) {
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/cuenta";		
		}else {
			/**
			 * Si no redireccionar al panel de control para 
			 * que la seguridad 
			 * pida sus clves de acceso
			 */
			return "redirect:/app/panelControl";
		}
	}
	
	
	/**
	 * 
	 * Solo el administrador puede dar de alta nuevos usuarios
	 * 
	 * @param idAdmin del usuario administrador que realiza la carga
	 * @param tipoDeUsuario
	 * @param nombre
	 * @param apellidos
	 * @param nif
	 * @param email
	 * @param telefono
	 * @param domicilio
	 * @param clave
	 * @param model
	 * @param redirect
	 * @return /usuarios/modulo
	 */
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
		/**
		 * Encriptar clave
		 */
		String encriptado = passwordEncoder.encode(clave); 
		/**
		 * Obtener objeto rolUsuario parseando el id recibido del formulario
		 */
		TiposUsuario tipUsuario = tudao.buscarTipoUsuario(Integer.parseInt(tipoDeUsuario));
		/**
		 * Cargar nuevo objeto del tipo usuario
		 */
		Usuario usuario = new Usuario(0, apellidos, encriptado, domicilio, email, nif, nombre, 1, telefono, idAdmin, tipUsuario);
		/**
		 * Si el alta devuelve null ya existe
		 * devolver los parametros para el estilo del mensaje de error
		 */
		if(udao.altaUsuario(usuario)==null) {
			redirect.addFlashAttribute("info", "El usuario ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			/**
			 * devolver los parametros para el estilo del mensaje de success
			 */
			redirect.addFlashAttribute("info", "Usuario dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/usuarios/modulo";
	}
	
	/**
	 * 
	 * Eliminar usuario si no tiene inmuebles asociados
	 * 
	 * @param idUsuario del usuario que se va a eliminar
	 * @param model
	 * @param redirect
	 * @return /usuarios/modulo
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto usuario
		 */
		Usuario usuario = udao.buscarUsuario(idUsuario);
		/**
		 * Listar inmuebles del usuario
		 */
		List<Inmueble> inmuebles = idao.buscarTodosPropietario(usuario);
		/**
		 * Si es igual a cero eliminar usuario y
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(inmuebles.size() == 0) {
			udao.eliminarUsuario(idUsuario);
			redirect.addFlashAttribute("info", "Usuario se ha eliminado correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}else {
			/**
			 * Si es igual tiene inmuebles
			 * redireccionar con mensaje a la vista
			 * y los parametros para el estilo del mensaje
			 */
			redirect.addFlashAttribute("info", "Este usuario tiene propiedades activas, asegurese de eliminar todas sus propiedades primero.");
			redirect.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/usuarios/modulo";
	}
	
	/**
	 * Acceder a los datos de un usuario para editar
	 * 
	 * @param idUsuario 
	 * @param model
	 * @param redirect
	 * @return usuarios/modulo o app/usuariosFicha
	 */
	@GetMapping("/verficha/{id}")
	public String verFichaUsuario(@PathVariable("id") int idUsuario, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto usuario
		 */
		Usuario usuario = udao.buscarUsuario(idUsuario);
		/**
		 * Si es igual a null
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(usuario == null) {
			redirect.addFlashAttribute("info", "El usuario no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}
		/**
		 * Si es igual no
		 * devolver los datos del usuario a la vista
		 */
		model.addAttribute("usuario", usuario);
		return "app/usuariosFicha";	
	}
	
	
	/**
	 * 
	 * Solo los adminitradores podrán editar datos de los usuarios
	 * 
	 * @param Datos del formulario de alta
	 * @param idUsuario a editar
	 * @param model
	 * @param redirect
	 * @return redirecciona a la vista principal de modulo de usuario
	 */
	@PostMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") int idUsuario,
								@RequestParam String nombre,
								@RequestParam String apellidos,
								@RequestParam String nif,
								@RequestParam String email,
								@RequestParam String telefono,
								@RequestParam String domicilio,
								Model model, RedirectAttributes redirect) {
		/**
		 * Buscar objeto usuario por id
		 */	
		Usuario usuario = udao.buscarUsuario(idUsuario);
		/**
		 * Si es null 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		if(usuario==null) {
			redirect.addFlashAttribute("info", "El usuario no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/usuarios/modulo";
		}
		/**
		 * Establecer los nuevos datos del objeto usuario
		 */	
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setNif(nif);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setDomicilio(domicilio);
		/**
		 * Editar el usuario
		 */	
		udao.editarUsuario(usuario);
		/**
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		redirect.addFlashAttribute("info", "Usuario modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		return "redirect:/usuarios/modulo";		
	}
}
