package com.edix.Proyecto_Final_Curso.controller;

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
import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.ProveedoresServicio;
import com.edix.Proyecto_Final_Curso.entities.TipoContrato;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresDao;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresServiciosDao;
import com.edix.Proyecto_Final_Curso.modeloDao.DateUtils;
import com.edix.Proyecto_Final_Curso.modeloDao.InmuebleDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Proveedores_servicioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_contratoDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;

/**
 * Controlador para crud de contratos de alquiler
 * 
 * El objetivo es llevar a cabo las operariones crud
 * y cargar listados relacionados con los alquileres
 * 
 * @author sabrycat
 * @version 1.0
 */
@RequestMapping("/alquileres")
@Controller
public class AlquileresController {
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private InmuebleDao idao;
	@Autowired
	private Tipo_contratoDao tcdao;
	@Autowired
	private AlquileresDao adao;
	@Autowired
	private Proveedores_servicioDao esdao;
	@Autowired
	private AlquileresServiciosDao asdao;
	
	/**
	 * 
	 * Cargar listados de contratos de alquiler dependiendo del tipo de usuario
	 * 
	 * @param idUsuario
	 * @param tipoUsuarioSession
	 * @param model
	 * @return app/alquileres
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
			 * Buscar contratos por admnistrador
			 * y listado de tiposcontratos, listado de inmuebles e inquilinos segun admin 
			 * para las altas de nuevos alquileres
			 */	
			List<Alquilere> contratos = adao.buscarTodosPorAdmin(administrador);
			model.addAttribute("contratos",contratos);
			List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
			model.addAttribute("tiposcontratos",tiposcontratos);
			List<Inmueble> inmuebles = idao.buscarTodosPorAdmin(administrador);
			System.out.println(inmuebles);
			model.addAttribute("inmuebles",inmuebles);
			List<Usuario> inquilinos = udao.buscarTodosInquilinos(idUsuario);
			model.addAttribute("inquilinos",inquilinos);
		}
		
		if(tipoUsuarioSession.equals("Propietario")) {
			Usuario propietario = udao.buscarUsuario(idUsuario);
			/**
			 * listar contratos e inquilinos segun propietario
			 */			
			List<Usuario> inquilinos = udao.buscarInquilinosPorPropietario(propietario);
			model.addAttribute("inquilinos",inquilinos);
			List<Alquilere> contratos = adao.buscarTodosPorPropietario(propietario);
			model.addAttribute("contratos",contratos);
		}
		
		if(tipoUsuarioSession.equals("Inquilino")) {
			Usuario inquilino = udao.buscarUsuario(idUsuario);
			/**
			 * listar contratos segun inquilinos
			 */	
			List<Alquilere> contratos = adao.buscarTodosPorInquilino(inquilino);
			model.addAttribute("contratos",contratos);
		}
		return "app/alquileres";
	}
	
	/**
	 * 
	 * alta nuevo contrato de alquiler
	 * 
	 * @param idInmueble
	 * @param usuarioInquilino
	 * @param tipoContrato
	 * @param importe
	 * @param fianza
	 * @param importeFianza
	 * @param fechaComienzo
	 * @param fechaFin
	 * @param renovacion
	 * @param observaciones
	 * @param model
	 * @param redirect
	 * @return
	 */
	@PostMapping("/altaNuevoContrato")
	public String altaNuevosAlquileres(@RequestParam String idInmueble,
									@RequestParam String usuarioInquilino,
									@RequestParam String tipoContrato,
									@RequestParam int importe,
									@RequestParam int fianza,
									@RequestParam int importeFianza,
									@RequestParam String fechaComienzo,
									@RequestParam String fechaFin,
									@RequestParam String renovacion,
									@RequestParam String observaciones,
									Model model, RedirectAttributes redirect
			) {

		/**
		 * Obtener objeto inmuebleAlquilado parseando el id recibido del formulario
		 */
		Inmueble inmuebleAlquilado = idao.buscarInmueble(Integer.parseInt(idInmueble));
		/**
		 * Obtener objeto inquilino parseando el id recibido del formulario
		 */
		Usuario inquilino = udao.buscarUsuario(Integer.parseInt(usuarioInquilino));
		/**
		 * Obtener objeto contrato parseando el id recibido del formulario
		 */
		TipoContrato contrato = tcdao.buscarTipoContrato(Integer.parseInt(tipoContrato));		
		/**
		 * Parseamos fechas con una funcion personalizada
		 */
		Date fechaC = DateUtils.parseStringToDate(fechaComienzo);
		Date fechaF = DateUtils.parseStringToDate(fechaFin);
		/**
		 * Cargar nuevo objeto del tipo Alquilere
		 */
		Alquilere nuevoContrato = new Alquilere(0, 0.0,fechaC,fechaF,fianza,
				(float) importe,(float) importeFianza,observaciones,
				renovacion,contrato,inquilino,inmuebleAlquilado
				);

		/**
		 * Si el alta devuelve null ya existe
		 * devolver los parametros para el estilo del mensaje de error
		 */
		if(adao.altaAlquiler(nuevoContrato)==null) {
			redirect.addFlashAttribute("info", "El contrato ya existe en nuestra plataforma");		
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			/**
			 * devolver los parametros para el estilo del mensaje de success
			 */
			redirect.addFlashAttribute("info", "Contrato dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/alquileres/modulo";		
	}
	
	/**
	 * 
	 * Acceder a los datos del alquiler para editar
	 * los propietarios solo podran ver
	 * 
	 * @param idContrato
	 * @param idUsuario
	 * @param tipoUsuarioSession
	 * @param model
	 * @param redirect
	 * @return /alquileres/modulo o app/alquileresFicha
	 */
	@GetMapping("/verContrato/{id}")
	public String verFichaAlquiler(@PathVariable("id") int idContrato, 
									@SessionAttribute("idUsuarioSession") int idUsuario,
									@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession,
									Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto Alquilere
		 */		
		Alquilere contrato = adao.buscarAlquiler(idContrato);
		/**
		 * Si es igual a null
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(contrato == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/alquileres/modulo";			
		}
		/**
		 * Si no
		 * cargamos los datos del contrato 
		 */
		model.addAttribute("contrato", contrato);
		/**
		 * cargamos los listados para cargar los select options
		 * segun tipo de usuario
		 */
		if(tipoUsuarioSession.equals("Administrador")) {
			/**
			 * listado de inquilinos segun administrador
			 */
			List<Usuario> inquilinos = udao.buscarTodosInquilinos(idUsuario);
			model.addAttribute("inquilinos",inquilinos);
		}
		if(tipoUsuarioSession.equals("Propietario")) {
			/**
			 * Obtener el objeto propietario
			 */	
			Usuario propietario = udao.buscarUsuario(idUsuario);
			/**
			 * listado de inquilinos segun propietario
			 */
			List<Usuario> inquilinos = udao.buscarInquilinosPorPropietario(propietario);
			model.addAttribute("inquilinos",inquilinos);
		}
		/**
		 * cargamos los listados tiposcontratos para carga los select options del formulario
		 */
		List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
		model.addAttribute("tiposcontratos",tiposcontratos);
		return "app/alquileresFicha";
	}
	
	/**
	 * Solo los adminitradores podrán editar datos de los del conotrato
	 * 
	 * @param idContrato
	 * @param usuarioInquilino
	 * @param tipoContrato
	 * @param importe
	 * @param fianza
	 * @param importeFianza
	 * @param fechaComienzo
	 * @param fechaFin
	 * @param renovacion
	 * @param observaciones
	 * @param model
	 * @param redirect
	 * @return /alquileres/modulo
	 */
	@PostMapping("/editarContrato/{id}")
	public String editarAlquiler(@PathVariable("id") int idContrato,
									@RequestParam String usuarioInquilino,
									@RequestParam String tipoContrato,
									@RequestParam String importe,
									@RequestParam int fianza,
									@RequestParam String importeFianza,
									@RequestParam String fechaComienzo,
									@RequestParam String fechaFin,
									@RequestParam String renovacion,
									@RequestParam String observaciones,
									Model model, RedirectAttributes redirect) {
		/**
		 * Buscar objeto Alquilere por idContrato
		 */	
		Alquilere alquiler = adao.buscarAlquiler(idContrato);
		/**
		 * Si es null 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		if(alquiler == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/alquileres/modulo";			
		}
		/**
		 * Si no
		 * Obtener objeto inquilino parseando el id recibido del formulario
		 */
		Usuario inquilino = udao.buscarUsuario(Integer.parseInt(usuarioInquilino));
		/**
		 * Obtener objeto TipoContrato parseando el id recibido del formulario
		 */
		TipoContrato contrato = tcdao.buscarTipoContrato(Integer.parseInt(tipoContrato));
		/**
		 * Parseamos fechas con una funcion personalizada
		 */
		Date fechaC = DateUtils.parseStringToDate(fechaComienzo);
		Date fechaF = DateUtils.parseStringToDate(fechaFin);
		/**
		 * Establecer los nuevos datos del objeto alquiler
		 */	
		alquiler.setUsuario(inquilino);
		alquiler.setTipoContrato(contrato);
		alquiler.setImporte(Float.parseFloat(importe));
		alquiler.setFianza(fianza);
		alquiler.setImporteFianza(Float.parseFloat(importeFianza));
		alquiler.setFechaComienzo(fechaC);
		alquiler.setFechaFin(fechaF);
		alquiler.setRenovacion(renovacion);
		alquiler.setObservaciones(observaciones);
		/**
		 * Editar el alquiler
		 */	
		adao.editarAlquiler(alquiler);
		redirect.addFlashAttribute("info", "Contrato modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		/**
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		return "redirect:/alquileres/modulo";	
	}

	/**
	 * 
	 * Ver servicios relacionados con un contrato de alquilere
	 * 
	 * @param idAlquiler
	 * @param model
	 * @param redirect
	 * @return app/alquileresServicios
	 */
	@GetMapping("/verServicios/{id}")
	public String alquilerServicios(@PathVariable("id") int idAlquiler, Model model, RedirectAttributes redirect) {
		
		List<ProveedoresServicio> proveedores = esdao.buscarTodos();
		model.addAttribute("proveedores", proveedores);	
		/**
		 * Obtener el objeto Alquilere
		 */
		Alquilere contrato = adao.buscarAlquiler(idAlquiler);
		/**
		 * devolver los datos del contrato a la vista
		 * y los listados servicios según contrato
		 */
		model.addAttribute("contrato", contrato);
		List<AlquilerServicio> servicios = asdao.buscarTodosPorAlquiler(contrato);
		model.addAttribute("servicios", servicios);
		model.addAttribute("linkmenu", "alquileres");
		return "app/alquileresServicios";
	}
	
	/**
	 * 
	 * Alta nuevo servicios a un contrato de alquiler
	 * 
	 * @param idAlquiler
	 * @param proveedoresServicio
	 * @param numeroContratoServicio
	 * @param fechaContratacion
	 * @param fechaFinalizacion
	 * @param baja
	 * @param model
	 * @param redirect
	 * @return /alquileres/verServicios/"+idAlquiler (devolver al mismo alquiler que se edito)
	 */
	@PostMapping("/altaNuevosServicios/{id}")
	public String altaNuevosServicios(@PathVariable("id") int idAlquiler,
										@RequestParam String proveedoresServicio,
										@RequestParam String numeroContratoServicio,
										@RequestParam String fechaContratacion,
										@RequestParam String fechaFinalizacion,
										@RequestParam String baja,
										Model model, RedirectAttributes redirect
										) {
		/**
		 * Obtener el objeto contrato segun idAlquiler
		 */
		Alquilere contrato = adao.buscarAlquiler(idAlquiler);
		/**
		 * Obtener objeto proveedor parseando el id recibido del formulario
		 */
		ProveedoresServicio proveedor = esdao.buscarProveedorServicio(Integer.parseInt(proveedoresServicio));
		/**
		 * Parseamos fechas con una funcion personalizada
		 */
		Date fechaC = DateUtils.parseStringToDate(fechaContratacion);
		Date fechaF = DateUtils.parseStringToDate(fechaFinalizacion);
		/**
		 * Cargar nuevo objeto del tipo AlquilerServicio
		 */
		AlquilerServicio nuevoservicio = new AlquilerServicio(0,baja,fechaC,fechaF,numeroContratoServicio,
											contrato,proveedor);
		/**
		 * Si el alta devuelve null ya existe
		 * devolver los parametros para el estilo del mensaje de error
		 */		
		if(asdao.altaAlquilerServicio(nuevoservicio)==null) {
			redirect.addFlashAttribute("info", "El servicio ya existe en este contrato de alquiler");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			/**
			 * devolver los parametros para el estilo del mensaje de success
			 */
			redirect.addFlashAttribute("info", "Servicio dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}		
		return "redirect:/alquileres/verServicios/"+idAlquiler;
	}
}
