package com.edix.Proyecto_Final_Curso.controller;

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

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.Provincia;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresDao;
import com.edix.Proyecto_Final_Curso.modeloDao.InmuebleDao;
import com.edix.Proyecto_Final_Curso.modeloDao.ProvinciasDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;


/**
 * Controlador para crud de inmuebles
 * 
 * El objetivo es llevar a cabo las operariones crud
 * y cargar listados relacionados con los inmuebles
 * 
 * @author sabrycat
 * @version 1.0
 */
@RequestMapping("/inmuebles")
@Controller
public class InmueblesController {
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private ProvinciasDao pdao;
	@Autowired
	private InmuebleDao idao;
	@Autowired
	private AlquileresDao adao;
	
	/**
	 * 
	 * Cargar listados de inmuebles dependiendo del tipo de usuario
	 * 
	 * @param idUsuario
	 * @param tipoUsuarioSession
	 * @param model
	 * @return app/inmuebles
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
			 * Buscar inmuebles, propietarios por admnistrador
			 * y listado de provincias para las altas nuevos inmuebles
			 */		
			List<Inmueble> inmuebles = idao.buscarTodosPorAdmin(administrador);
			model.addAttribute("inmuebles",inmuebles);
			List<Usuario> propietarios = udao.buscarTodosPropietarios(idUsuario);
			model.addAttribute("propietarios",propietarios);
			List<Provincia> provincias = pdao.buscarTodas();
			model.addAttribute("provincias", provincias);
		}
		
		if(tipoUsuarioSession.equals("Propietario")) {
			Usuario propietario = udao.buscarUsuario(idUsuario);
			/**
			 * Buscar inmuebles, propietarios por propietario
			 */	
			List<Inmueble> inmuebles = idao.buscarTodosPropietario(propietario);
			model.addAttribute("inmuebles",inmuebles);
		}
		
		if(tipoUsuarioSession.equals("Inquilino")) {
			Usuario inquilino = udao.buscarUsuario(idUsuario);
			/**
			 * Buscar inmuebles, propietarios por inquilino
			 */	
			List<Inmueble> inmuebles = idao.buscarTodosInquilino(inquilino);
			model.addAttribute("inmuebles",inmuebles);
		}
		
		return "app/inmuebles";
	}
	
	/**
	 * 
	 * Listar los contratos segun el inmueble seleccionado
	 * 
	 * @param idInmueble
	 * @param model
	 * @param redirect
	 * @return /inmuebles/modulo o app/alquileresInmueble
	 */
	@GetMapping("/verContratos/{id}")
	public String inmueblesContratos(@PathVariable("id") int idInmueble, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto inmueble
		 */
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		/**
		 * Listar alquileres segun inmueble
		 */
		List<Alquilere> contratos = adao.buscarAlquilerPorInmueble(inmueble);
		/**
		 * Si es igual a cero 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(contratos.size() == 0) {
			redirect.addFlashAttribute("info", "Este inmueble no tiene contrato activo");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}else {
			/**
			 * Si no
			 * Redireccionar a la vista
			 * con los contratos y datos del inmueble
			 * y parametro para que el sidebar mantenga el color 
			 * del modulo de inmuebles
			 */
			model.addAttribute("inmueble",inmueble);
			model.addAttribute("contratos",contratos);
			model.addAttribute("linkmenu", "inmuebles");
			return "app/alquileresInmueble";
		}
	}
	
	/**
	 * 
	 * Alta nuevo inmueble con id de adminitrador
	 * 
	 * @param idAdmin 
	 * @param propietario
	 * @param numeroCatastral
	 * @param anioContruccion
	 * @param tipoFinca
	 * @param direccion
	 * @param provincia
	 * @param metros
	 * @param ascensor
	 * @param habitaciones
	 * @param banios
	 * @param plazaGarage
	 * @param zonaExterior
	 * @param observaciones
	 * @param model
	 * @param redirect
	 * @return /inmuebles/modulo
	 */
	@PostMapping("/altaNuevosInmueble")
	public String altaNuevosInmueble(@SessionAttribute("idUsuarioSession") int idAdmin,
										@RequestParam String propietario,
										@RequestParam String numeroCatastral,
										@RequestParam String anioContruccion,
										@RequestParam String tipoFinca,
										@RequestParam String direccion,
										@RequestParam String provincia,
										@RequestParam String metros,
										@RequestParam String ascensor,
										@RequestParam String habitaciones,
										@RequestParam String banios,
										@RequestParam String plazaGarage,
										@RequestParam String zonaExterior,
										@RequestParam String observaciones,
										Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto usuario admistrador
		 */
		Usuario administrador = udao.buscarUsuario(idAdmin);
		/**
		 * Obtener objeto propietarioSeleccionado parseando el id recibido del formulario
		 */
		Usuario propietarioSeleccionado = udao.buscarUsuario(Integer.parseInt(propietario));
		/**
		 * Obtener objeto provinciaSeleccionada parseando el id recibido del formulario
		 */
		Provincia provinciaSeleccionada = pdao.buscarProvincia(Integer.parseInt(provincia));
		/**
		 * Cargar nuevo objeto del tipo inmueble
		 */
		Inmueble inmueble = new Inmueble(0,anioContruccion,ascensor,Integer.parseInt(banios),direccion,
										Integer.parseInt(habitaciones),Float.parseFloat(metros), numeroCatastral, observaciones,
										plazaGarage, tipoFinca, zonaExterior,provinciaSeleccionada,propietarioSeleccionado,
										administrador);
		/**
		 * Si el alta devuelve null ya existe
		 * devolver los parametros para el estilo del mensaje de error
		 */
		if(idao.altaInmueble(inmueble)==null) {
			redirect.addFlashAttribute("info", "El inmueble ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			/**
			 * devolver los parametros para el estilo del mensaje de success
			 */
			redirect.addFlashAttribute("info", "Inmueble dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/inmuebles/modulo";
	}
	
	/**
	 * 
	 * Acceder a los datos del inmueble para editar
	 * 
	 * @param idInmueble
	 * @param idAdmin
	 * @param model
	 * @param redirect
	 * @return app/inmueblesFicha o /inmuebles/modulo
	 */
	@GetMapping("/verficha/{id}")
	public String verFichaInmueble(@PathVariable("id") int idInmueble, 
									@SessionAttribute("idUsuarioSession") int idAdmin,
									Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto inmueble
		 */
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		/**
		 * Si es igual a null
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */
		if(inmueble == null) {
			redirect.addFlashAttribute("info", "El inmueble no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}
		/**
		 * Si no
		 * devolver los datos del inmueble a la vista
		 * y los listados provincias y propietarios según usuario adminitrador
		 * para cargar los select options
		 */
		model.addAttribute("inmueble", inmueble);
		List<Provincia> provincias = pdao.buscarTodas();
		model.addAttribute("provincias", provincias);
		List<Usuario> propietarios = udao.buscarTodosPropietarios(idAdmin);
		model.addAttribute("propietarios",propietarios);
		return "app/inmueblesFicha";	
	}
	
	/**
	 * 
	 * Solo los adminitradores podrán editar datos de los inmuebles
	 * 
	 * @param idInmueble
	 * @param propietario
	 * @param numeroCatastral
	 * @param anioContruccion
	 * @param tipoFinca
	 * @param direccion
	 * @param provincia
	 * @param metros
	 * @param ascensor
	 * @param habitaciones
	 * @param banios
	 * @param plazaGarage
	 * @param zonaExterior
	 * @param observaciones
	 * @param model
	 * @param redirect
	 * @return /inmuebles/modulo
	 */
	@PostMapping("/editarInmueble/{id}")
	public String editarInmueble(@PathVariable("id") int idInmueble,
									@RequestParam String propietario,
									@RequestParam String numeroCatastral,
									@RequestParam String anioContruccion,
									@RequestParam String tipoFinca,
									@RequestParam String direccion,
									@RequestParam String provincia,
									@RequestParam String metros,
									@RequestParam String ascensor,
									@RequestParam String habitaciones,
									@RequestParam String banios,
									@RequestParam String plazaGarage,
									@RequestParam String zonaExterior,
									@RequestParam String observaciones,
									Model model, RedirectAttributes redirect) {
		
		/**
		 * Buscar objeto inmueble por idInmueble
		 */	
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		/**
		 * Si es null 
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		if(inmueble==null) {
			redirect.addFlashAttribute("info", "El inmueble no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}
		
		/**
		 * Si no
		 * Obtener objeto propietarioSeleccionado parseando el id recibido del formulario
		 */
		Usuario propietarioSeleccionado = udao.buscarUsuario(Integer.parseInt(propietario));
		/**
		 * Obtener objeto provinciaSeleccionada parseando el id recibido del formulario
		 */
		Provincia provinciaSeleccionada = pdao.buscarProvincia(Integer.parseInt(provincia));
		/**
		 * Establecer los nuevos datos del objeto inmueble
		 */	
		inmueble.setUsuario(propietarioSeleccionado);
		inmueble.setnumeroCatastral(numeroCatastral);
		inmueble.setAnioContruccion(anioContruccion);
		inmueble.setTipoFinca(tipoFinca);
		inmueble.setDireccion(direccion);
		inmueble.setProvincia(provinciaSeleccionada);
		inmueble.setMetros(Float.parseFloat(metros));
		inmueble.setAscensor(ascensor);
		inmueble.setHabitaciones(Integer.parseInt(habitaciones));
		inmueble.setBanios(Integer.parseInt(banios));
		inmueble.setPlazaGarage(plazaGarage);
		inmueble.setZonaExterior(zonaExterior);
		inmueble.setObservaciones(observaciones);
		/**
		 * Editar el inmueble
		 */	
		idao.editarInmueble(inmueble);
		redirect.addFlashAttribute("info", "Inmueble modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		/**
		 * Redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */	
		return "redirect:/inmuebles/modulo";	
	}
	
	/**
	 * 
	 * Eliminar inmueble si no tiene contrato asociado
	 * 
	 * @param idInmueble del inmueble que se va a eliminar
	 * @param model
	 * @param redirect
	 * @return /inmuebles/modulo
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarInmueble(@PathVariable("id") int idInmueble, Model model, RedirectAttributes redirect) {
		/**
		 * Obtener el objeto inmueble
		 */
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		/**
		 * Listar contratos segun inmueble
		 */		
		List<Alquilere> contrato = adao.buscarAlquilerPorInmueble(inmueble);	
		/**
		 * Si es igual a cero eliminar inmueble y
		 * redireccionar con mensaje a la vista
		 * y los parametros para el estilo del mensaje
		 */		
		if(contrato.size() == 0) {
			idao.eliminarInmueble(idInmueble);
			redirect.addFlashAttribute("info", "Inmueble se ha eliminado correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}else {
			/**
			 * Si es igual tiene contratos
			 * redireccionar con mensaje a la vista
			 * y los parametros para el estilo del mensaje
			 */
			redirect.addFlashAttribute("info", "Este inmueble tiene contratos activos, asegurese de eliminarlos primero.");
			redirect.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/inmuebles/modulo";
	}

}
