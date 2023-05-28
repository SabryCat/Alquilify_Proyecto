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
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresServiciosDao;
import com.edix.Proyecto_Final_Curso.modeloDao.DateUtils;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;
@RequestMapping("/servicios")
@Controller
public class ServiciosController {
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private AlquileresServiciosDao asdao;
	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idAdmin, Model model) {
		Usuario administrador = udao.buscarUsuario(idAdmin);
		List<AlquilerServicio> servicios = asdao.buscarTodosPorAdministrador(administrador);
		model.addAttribute("servicios", servicios);
		return "app/servicios";
	}
	
	@GetMapping("/verficha/{id}")
	public String verFichaServicio(@PathVariable("id") int idServicio, 
									@SessionAttribute("idUsuarioSession") int idAdmin,
									Model model, RedirectAttributes redirect) {
		AlquilerServicio servicio = asdao.buscarAlquilerServicio(idServicio);
		if(servicio == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/servicios/modulo";			
		}
		model.addAttribute("servicio", servicio);
		return "app/serviciosFicha";
	}	
	
	
	@PostMapping("/editarServicios/{id}")
	public String editarServicios(@PathVariable("id") int idServicio,
									@RequestParam String numeroContratoServicio,
									@RequestParam String fechaContratacion,
									@RequestParam String fechaFinalizacion,
									@RequestParam String baja,
									Model model, RedirectAttributes redirect
									) {
		AlquilerServicio servicio = asdao.buscarAlquilerServicio(idServicio);
		
		if(servicio == null) {	
			redirect.addFlashAttribute("info", "El servicio no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
		}
		Date fechaC = DateUtils.parseStringToDate(fechaContratacion);
		Date fechaF = DateUtils.parseStringToDate(fechaFinalizacion);
		servicio.setNumeroContratoServicio(numeroContratoServicio);
		servicio.setFechaContratacion(fechaC);
		servicio.setFechaFinalizacion(fechaF);
		servicio.setBaja(baja);
		asdao.editarAlquilerServicio(servicio);
		redirect.addFlashAttribute("info", "Servicio modificado de alta correctamente");
		redirect.addFlashAttribute("tipo", "success");

		return "redirect:/servicios/modulo";
	}

}
