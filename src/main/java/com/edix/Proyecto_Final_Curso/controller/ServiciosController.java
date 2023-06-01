package com.edix.Proyecto_Final_Curso.controller;

/**
 * Controlador para crud de servicios 
 * 
 * El objetivo es llevar a cabo las operariones crud
 * y cargar listados relacionados con los servicios (sumistros/seguros)
 * 
 * @author sabrycat
 * @version 1.0
 */
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.Proyecto_Final_Curso.entities.AlquilerServicio;
import com.edix.Proyecto_Final_Curso.entities.TiposServicio;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresServiciosDao;
import com.edix.Proyecto_Final_Curso.modeloDao.DateUtils;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_servicioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;
@RequestMapping("/servicios")
@Controller
public class ServiciosController {
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private AlquileresServiciosDao asdao;
	@Autowired
	private Tipo_servicioDao tsdao;
	
	/**
	 * 
	 * Cargar listados de servicios dependiendo del tipo de usuario 
	 * 
	 * @param idUsuario
	 * @param tipoUsuarioSession
	 * @param model
	 * @return app/servicios
	 */
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idUsuario, 
						@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession, Model model) {
		
		/**
		 * Comparar tipo de usuario para cargar listados
		 */	
		if(tipoUsuarioSession.equals("Administrador")) {
			Usuario administrador = udao.buscarUsuario(idUsuario);
			/**
			 * Buscar servicios por admnistrador
			 * y listado de tiposservicios
			 * para las altas de nuevos servicios
			 */	
			List<AlquilerServicio> servicios = asdao.buscarTodosPorAdministrador(administrador);
			model.addAttribute("servicios", servicios);
			List<TiposServicio> tiposservicios = tsdao.buscarTodos();
			model.addAttribute("tiposservicios", tiposservicios);
		}
		
		if(tipoUsuarioSession.equals("Inquilino")) {		
			Usuario inquilino = udao.buscarUsuario(idUsuario);
			/**
			 * listar servicios segun inquilino
			 */		
			List<AlquilerServicio> servicios = asdao.buscarTodosPorInquilino(inquilino);
			model.addAttribute("servicios", servicios);
		}		
		return "app/servicios";					
	}
	
	/**
	 * 
	 * Acceder a los datos del servicios, solo administradores podran los  editar
	 *   
	 * 
	 * @param idServicio
	 * @param idAdmin
	 * @param model
	 * @param redirect
	 * @return app/serviciosFicha o /servicios/modulo
	 */
	@GetMapping("/verficha/{id}")
	public String verFichaServicio(@PathVariable("id") int idServicio, 
									@SessionAttribute("idUsuarioSession") int idAdmin,
									Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto AlquilerServicio
		 */		
		AlquilerServicio servicio = asdao.buscarAlquilerServicio(idServicio);
		/**
		 * Si es igual a null
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(servicio == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/servicios/modulo";			
		}
		/**
		 * Si no
		 * cargamos los datos del servicio 
		 */
		model.addAttribute("servicio", servicio);
		return "app/serviciosFicha";
	}	
	
	/**
	 * 
	 * @param idServicio
	 * @param numeroContratoServicio
	 * @param fechaContratacion
	 * @param fechaFinalizacion
	 * @param baja
	 * @param model
	 * @param redirect
	 * @return
	 */
	@PostMapping("/editarServicios/{id}")
	public String editarServicios(@PathVariable("id") int idServicio,
									@RequestParam String numeroContratoServicio,
									@RequestParam String fechaContratacion,
									@RequestParam String fechaFinalizacion,
									@RequestParam String baja,
									Model model, RedirectAttributes redirect
									) {
		/**
		 * Buscar objeto AlquilerServicio por idServicio
		 */	
		AlquilerServicio servicio = asdao.buscarAlquilerServicio(idServicio);
		/**
		 * Si es null 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		if(servicio == null) {	
			redirect.addFlashAttribute("info", "El servicio no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
		}
		/**
		 * Parseamos fechas con una funcion personalizada
		 */
		Date fechaC = DateUtils.parseStringToDate(fechaContratacion);
		Date fechaF = DateUtils.parseStringToDate(fechaFinalizacion);
		/**
		 * Establecer los nuevos datos del objeto servicio
		 */	
		servicio.setNumeroContratoServicio(numeroContratoServicio);
		servicio.setFechaContratacion(fechaC);
		servicio.setFechaFinalizacion(fechaF);
		servicio.setBaja(baja);
		/**
		 * Editar el servicio
		 */	
		asdao.editarAlquilerServicio(servicio);
		redirect.addFlashAttribute("info", "Servicio modificado de alta correctamente");
		redirect.addFlashAttribute("tipo", "success");
		/**
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		return "redirect:/servicios/modulo";
	}

}
