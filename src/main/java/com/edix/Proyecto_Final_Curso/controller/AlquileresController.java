package com.edix.Proyecto_Final_Curso.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.Proyecto_Final_Curso.entities.Alquilere;
import com.edix.Proyecto_Final_Curso.entities.Inmueble;
import com.edix.Proyecto_Final_Curso.entities.TipoContrato;
import com.edix.Proyecto_Final_Curso.entities.Usuario;
import com.edix.Proyecto_Final_Curso.modeloDao.AlquileresDao;
import com.edix.Proyecto_Final_Curso.modeloDao.DateUtils;
import com.edix.Proyecto_Final_Curso.modeloDao.InmuebleDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_contratoDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;

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
	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idAdmin, Model model) {
		Usuario administrador = udao.buscarUsuario(idAdmin);
		List<Inmueble> inmuebles = idao.buscarTodosPorAdmin(administrador);
		System.out.println(inmuebles);
		model.addAttribute("inmuebles",inmuebles);
		List<Usuario> inquilinos = udao.buscarTodosInquilinos(idAdmin);
		model.addAttribute("inquilinos",inquilinos);
		List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
		model.addAttribute("tiposcontratos",tiposcontratos);
		return "app/alquileres";
	}
	
	@PostMapping("/altaNuevoContrato")
	public String altaNuevosInmueble(@RequestParam String idInmueble,
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
		//actualizacionRenta
		// cast int a double
		
		Inmueble inmuebleAlquilado = idao.buscarInmueble(Integer.parseInt(idInmueble));
		Usuario inquilino = udao.buscarUsuario(Integer.parseInt(usuarioInquilino));
		TipoContrato contrato = tcdao.buscarTipoContrato(Integer.parseInt(tipoContrato));
		
		
		Date fechaC = DateUtils.parseStringToDate(fechaComienzo);
		Date fechaF = DateUtils.parseStringToDate(fechaComienzo);
		
		
		Alquilere nuevoContrato = new Alquilere(0, 0.0,fechaC,fechaF,fianza,
				(float) importe,(float) importeFianza,observaciones,
				renovacion,contrato,inquilino,inmuebleAlquilado
				);
		
		if(adao.altaAlquiler(nuevoContrato)==null) {
			redirect.addFlashAttribute("info", "El contrato ya existe en nuestra plataforma");		
		}else {
			redirect.addFlashAttribute("info", "Contrato dado de alta correctamente");
		}
		return "redirect:/alquileres/modulo";
		
	}
	
	
	
}
