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
	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idUsuario, 
						@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession, Model model) {
		if(tipoUsuarioSession.equals("Administrador")) {
		Usuario administrador = udao.buscarUsuario(idUsuario);
		List<Inmueble> inmuebles = idao.buscarTodosPorAdmin(administrador);
		model.addAttribute("inmuebles",inmuebles);
		List<Usuario> propietarios = udao.buscarTodosPropietarios(idUsuario);
		model.addAttribute("propietarios",propietarios);
		List<Provincia> provincias = pdao.buscarTodas();
		model.addAttribute("provincias", provincias);
		}
		if(tipoUsuarioSession.equals("Propietario")) {
			Usuario propietario = udao.buscarUsuario(idUsuario);
			List<Inmueble> inmuebles = idao.buscarTodosPropietario(propietario);
			model.addAttribute("inmuebles",inmuebles);
		}
		return "app/inmuebles";
	}
	
	@GetMapping("/verContratos/{id}")
	public String inmueblesContratos(@PathVariable("id") int idInmueble, Model model, RedirectAttributes redirect) {
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		List<Alquilere> contratos = adao.buscarAlquilerPorInmueble(inmueble);
		System.out.println(contratos);
		if(contratos.size() == 0) {
			redirect.addFlashAttribute("info", "Este inmueble no tiene contrato activo");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}else {
			model.addAttribute("inmueble",inmueble);
			model.addAttribute("contratos",contratos);
			model.addAttribute("linkmenu", "inmuebles");
			return "app/alquileresInmueble";
		}
	}
	
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
		
		Usuario administrador = udao.buscarUsuario(idAdmin);
		Usuario propietarioSeleccionado = udao.buscarUsuario(Integer.parseInt(propietario));
		Provincia provinciaSeleccionada = pdao.buscarProvincia(Integer.parseInt(provincia));
		Inmueble inmueble = new Inmueble(0,anioContruccion,ascensor,Integer.parseInt(banios),direccion,
										Integer.parseInt(habitaciones),Float.parseFloat(metros), numeroCatastral, observaciones,
										plazaGarage, tipoFinca, zonaExterior,provinciaSeleccionada,propietarioSeleccionado,
										administrador);
		
		if(idao.altaInmueble(inmueble)==null) {
			redirect.addFlashAttribute("info", "El inmueble ya existe en nuestra plataforma");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			redirect.addFlashAttribute("info", "Inmueble dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/inmuebles/modulo";
	}
	
	@GetMapping("/verficha/{id}")
	public String verFichaInmueble(@PathVariable("id") int idInmueble, 
									@SessionAttribute("idUsuarioSession") int idAdmin,
									Model model, RedirectAttributes redirect) {
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		if(inmueble == null) {
			redirect.addFlashAttribute("info", "El inmueble no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}
		model.addAttribute("inmueble", inmueble);
		List<Provincia> provincias = pdao.buscarTodas();
		model.addAttribute("provincias", provincias);
		List<Usuario> propietarios = udao.buscarTodosPropietarios(idAdmin);
		model.addAttribute("propietarios",propietarios);
		return "app/inmueblesFicha";	
	}
	
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
		
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		if(inmueble==null) {
			redirect.addFlashAttribute("info", "El inmueble no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/inmuebles/modulo";
		}
		
		Usuario propietarioSeleccionado = udao.buscarUsuario(Integer.parseInt(propietario));
		Provincia provinciaSeleccionada = pdao.buscarProvincia(Integer.parseInt(provincia));
		
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
		idao.editarInmueble(inmueble);
		redirect.addFlashAttribute("info", "Inmueble modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		return "redirect:/inmuebles/modulo";	
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarInmueble(@PathVariable("id") int idInmueble, Model model, RedirectAttributes redirect) {
		Inmueble inmueble = idao.buscarInmueble(idInmueble);
		List<Alquilere> contrato = adao.buscarAlquilerPorInmueble(inmueble);		
		if(contrato.size() == 0) {
			idao.eliminarInmueble(idInmueble);
			redirect.addFlashAttribute("info", "Inmueble se ha eliminado correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}else {
			redirect.addFlashAttribute("info", "Este inmueble tiene contratos activos, asegurese de eliminarlos primero.");
			redirect.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/inmuebles/modulo";
	}

}
