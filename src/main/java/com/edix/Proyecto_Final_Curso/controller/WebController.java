package com.edix.Proyecto_Final_Curso.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_usuarioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.VencimientoProjection;
import com.edix.Proyecto_Final_Curso.modeloDao.VencimientoServiciosProjection;
import com.edix.Proyecto_Final_Curso.modeloDao.WebPanelDao;

/**
 * Controlador para el sitio web.
 * 
 * El objetivo de esta clase es manejar las vistas del sitio web.
 * Tambien cargara un listado de tipo de usuarios para el alta desde la web.
 * Ademas podremos acceder a la aplicacion de Alquilify con login de seguridad
 * 
 * @author sabrycat
 * @version 1.0
 */
@RequestMapping("/")
@Controller
public class WebController {

	@Autowired
	private Tipo_usuarioDao tudao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private WebPanelDao wpcdao;

	/**
	 * Ruta al HTML de contacto.
	 * 
	 * @return HTML de contacto.
	 */
	@GetMapping("contacto")
	public String contacto() {
		return "contacto";
	}

	/**
	 * Ruta a la aplicacion con usuario demo.
	 * 
	 * @return HTML de la aplicacion demo.
	 */
	@GetMapping("demo")
	public String demo() {
		return "app/demo";
	}

	/**
	 * Ruta al HTML de nosotros.
	 * 
	 * @return HTML de nosotros.
	 */
	@GetMapping("nosotros")
	public String nosotros() {
		return "nosotros";
	}

	/**
	 * Ruta al HTML de cuenta de usuario y acceso a la aplicacion.
	 * 
	 * Envia el listado de tipos de usuario para el alta desde la web
	 * 
	 * @param model El modelo de datos para la vista.
	 * @return HTML de cuenta de usuario con listado de tipos de usuario.
	 */
	@GetMapping("cuenta")
	public String cuenta(Model model) {
		List<TiposUsuario> tipousuario = tudao.buscarTodos();
		model.addAttribute("tipousuario", tipousuario);
		return "cuenta";
	}

	/**
	 * Ruta al HTML de servicios.
	 * 
	 * @return HTML de servicios.
	 */
	@GetMapping("servicios")
	public String servicios() {
		return "servicios";
	}

	/**
	 * Ruta al HTML de inicio (home).
	 * 
	 * @return HTML de inicio.
	 */
	@GetMapping("/")
	public String home() {
		return "index";
	}

	/**
	 * Ruta al panel de control de la app Alquiliy.
	 * 
	 * Se encarga de autenticar en la aplicacion principal
	 * y de cargar datos en la session del usuario.
	 * Identifica el tipo de usuario y segun esto cargara
	 * los listados con el control de vencimientos
	 * para los contratos de alquiler y servicios.
	 * 
	 * @param aut        La autenticacion del usuario.
	 * @param model      El modelo de datos para la vista.
	 * @param misession  La session HTTP actual.
	 * @return HTML del panel de control.
	 */
	@GetMapping("app/panelControl")
	public String panelControl(Authentication aut, Model model, HttpSession misession) {
		/**
		 * Buscar usuario por email
		 */
		Usuario usuario = udao.buscarByEmail(aut.getName());
		/**
		 * Obtener y cargar datos del usuario en session
		 */
		String tipoUsuarioSession = usuario.getTiposUsuario().getTipo();
		int idUsuario = usuario.getIdUsuario();
		misession.setAttribute("idUsuarioSession", idUsuario);
		misession.setAttribute("nombreUsuarioSession", usuario.getNombre());
		misession.setAttribute("tipoUsuarioSession", tipoUsuarioSession);
		/**
		 * Crear objeto usuarioActual con el id de session
		 */
		Usuario usuarioActual = udao.buscarUsuario(idUsuario);	
		/**
		 * NOTA: para obtener estos listados se utlizan
		 * 	proyecciones en el contexto de Java y Spring
		 *  para recibir el objeto y un valor extra desde la 
		 *  query del repositorio
		 *  VencimientoProjection
		 *  VencimientoServiciosProjection
		 *  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections
		 */				 
		/**
		 * Comparar tipo de usuario para cargar listados
		 * Buscar contratos y servicios segun tipo de usuario
		 * y utiliza el objeto usuarioActual para enviar a la query del repositorios
		 */	
		if (tipoUsuarioSession.equals("Administrador")) {
			List<VencimientoProjection> contratos = wpcdao.buscarFechasVencimiento(usuarioActual);
			model.addAttribute("contratos", contratos);
			List<VencimientoServiciosProjection> servicios = wpcdao.buscarFechasVencimientoServicios(usuarioActual);
			model.addAttribute("servicios", servicios);
		}

		if (tipoUsuarioSession.equals("Propietario")) {
			List<VencimientoProjection> contratos = wpcdao.buscarVencimientoPropietario(usuarioActual);
			model.addAttribute("contratos", contratos);
			System.out.println(" Batmannn" + contratos);
			List<VencimientoServiciosProjection> servicios = wpcdao.buscarVencimientoServiciosProp(usuarioActual);
			model.addAttribute("servicios", servicios);
		}

		if (tipoUsuarioSession.equals("Inquilino")) {
			List<VencimientoProjection> contratos = wpcdao.buscarVencimientoInqui(usuarioActual);
			model.addAttribute("contratos", contratos);
			List<VencimientoServiciosProjection> servicios = wpcdao.buscarVencimientoServiciosInqui(usuarioActual);
			model.addAttribute("servicios", servicios);
		}
		return "app/panelControl";
	}
}
