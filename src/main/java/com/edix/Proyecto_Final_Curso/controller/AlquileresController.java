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
	
	@GetMapping("/modulo")
	public String home(@SessionAttribute("idUsuarioSession") int idUsuario, 
						@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession, Model model) {
		
		if(tipoUsuarioSession.equals("Administrador")) {
			Usuario administrador = udao.buscarUsuario(idUsuario);
			List<Inmueble> inmuebles = idao.buscarTodosPorAdmin(administrador);
			System.out.println(inmuebles);
			model.addAttribute("inmuebles",inmuebles);
			List<Usuario> inquilinos = udao.buscarTodosInquilinos(idUsuario);
			model.addAttribute("inquilinos",inquilinos);
			List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
			model.addAttribute("tiposcontratos",tiposcontratos);
			List<Alquilere> contratos = adao.buscarTodosPorAdmin(administrador);
			model.addAttribute("contratos",contratos);
		}
		
		if(tipoUsuarioSession.equals("Propietario")) {
			Usuario propietario = udao.buscarUsuario(idUsuario);
			List<Usuario> inquilinos = udao.buscarInquilinosPorPropietario(propietario);
			model.addAttribute("inquilinos",inquilinos);
			List<Alquilere> contratos = adao.buscarTodosPorPropietario(propietario);
			model.addAttribute("contratos",contratos);
		}
		
		if(tipoUsuarioSession.equals("Inquilino")) {
			Usuario inquilino = udao.buscarUsuario(idUsuario);
			List<Alquilere> contratos = adao.buscarTodosPorInquilino(inquilino);
			model.addAttribute("contratos",contratos);
		}
		return "app/alquileres";
	}
	
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

		//Obtenenmos los objetos según id
		Inmueble inmuebleAlquilado = idao.buscarInmueble(Integer.parseInt(idInmueble));
		Usuario inquilino = udao.buscarUsuario(Integer.parseInt(usuarioInquilino));
		TipoContrato contrato = tcdao.buscarTipoContrato(Integer.parseInt(tipoContrato));
		//Parseamos fechas con una funcion personalizada
		Date fechaC = DateUtils.parseStringToDate(fechaComienzo);
		Date fechaF = DateUtils.parseStringToDate(fechaFin);
		//Damos de alta el nuevo contrato		
		Alquilere nuevoContrato = new Alquilere(0, 0.0,fechaC,fechaF,fianza,
				(float) importe,(float) importeFianza,observaciones,
				renovacion,contrato,inquilino,inmuebleAlquilado
				);
		//Comprobamos que se dio de alta correctamente
		if(adao.altaAlquiler(nuevoContrato)==null) {
			redirect.addFlashAttribute("info", "El contrato ya existe en nuestra plataforma");		
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			redirect.addFlashAttribute("info", "Contrato dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		return "redirect:/alquileres/modulo";
		
	}
	
	@GetMapping("/verContrato/{id}")
	public String verFichaAlquiler(@PathVariable("id") int idContrato, 
									@SessionAttribute("idUsuarioSession") int idUsuario,
									@SessionAttribute("tipoUsuarioSession") String tipoUsuarioSession,
									Model model, RedirectAttributes redirect) {
		Alquilere contrato = adao.buscarAlquiler(idContrato);
		if(contrato == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede ver");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/alquileres/modulo";			
		}
		model.addAttribute("contrato", contrato);
		
		if(tipoUsuarioSession.equals("Administrador")) {
			List<Usuario> inquilinos = udao.buscarTodosInquilinos(idUsuario);
			model.addAttribute("inquilinos",inquilinos);
		}
		if(tipoUsuarioSession.equals("Propietario")) {
			Usuario propietario = udao.buscarUsuario(idUsuario);
			List<Usuario> inquilinos = udao.buscarInquilinosPorPropietario(propietario);
			model.addAttribute("inquilinos",inquilinos);
		}
		List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
		model.addAttribute("tiposcontratos",tiposcontratos);
		return "app/alquileresFicha";
	}
	
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
		Alquilere alquiler = adao.buscarAlquiler(idContrato);
		if(alquiler == null) {
			redirect.addFlashAttribute("info", "El contrato no existe y no se puede modificar");
			redirect.addFlashAttribute("tipo", "danger");
			return "redirect:/alquileres/modulo";			
		}
		Usuario inquilino = udao.buscarUsuario(Integer.parseInt(usuarioInquilino));
		TipoContrato contrato = tcdao.buscarTipoContrato(Integer.parseInt(tipoContrato));
		
		Date fechaC = DateUtils.parseStringToDate(fechaComienzo);
		Date fechaF = DateUtils.parseStringToDate(fechaFin);

		alquiler.setUsuario(inquilino);
		alquiler.setTipoContrato(contrato);
		alquiler.setImporte(Float.parseFloat(importe));
		alquiler.setFianza(fianza);
		alquiler.setImporteFianza(Float.parseFloat(importeFianza));
		alquiler.setFechaComienzo(fechaC);
		alquiler.setFechaFin(fechaF);
		alquiler.setRenovacion(renovacion);
		alquiler.setObservaciones(observaciones);
		adao.editarAlquiler(alquiler);
		redirect.addFlashAttribute("info", "Contrato modificado correctamente");
		redirect.addFlashAttribute("tipo", "success");
		return "redirect:/alquileres/modulo";	
	}

	@GetMapping("/verServicios/{id}")
	public String alquilerServicios(@PathVariable("id") int idAlquiler, Model model, RedirectAttributes redirect) {
		List<ProveedoresServicio> proveedores = esdao.buscarTodos();
		model.addAttribute("proveedores", proveedores);		
		Alquilere contrato = adao.buscarAlquiler(idAlquiler);
		model.addAttribute("contrato", contrato);
		List<AlquilerServicio> servicios = asdao.buscarTodosPorAlquiler(contrato);
		model.addAttribute("servicios", servicios);
		model.addAttribute("linkmenu", "alquileres");
		return "app/alquileresServicios";
	}
	
	@PostMapping("/altaNuevosServicios/{id}")
	public String altaNuevosServicios(@PathVariable("id") int idAlquiler,
										@RequestParam String proveedoresServicio,
										@RequestParam String numeroContratoServicio,
										@RequestParam String fechaContratacion,
										@RequestParam String fechaFinalizacion,
										@RequestParam String baja,
										Model model, RedirectAttributes redirect
										) {
		Alquilere contrato = adao.buscarAlquiler(idAlquiler);
		ProveedoresServicio proveedor = esdao.buscarProveedorServicio(Integer.parseInt(proveedoresServicio));

		Date fechaC = DateUtils.parseStringToDate(fechaContratacion);
		Date fechaF = DateUtils.parseStringToDate(fechaFinalizacion);
		
		AlquilerServicio nuevoservicio = new AlquilerServicio(0,baja,fechaC,fechaF,numeroContratoServicio,
											contrato,proveedor);
		
		if(asdao.altaAlquilerServicio(nuevoservicio)==null) {
			redirect.addFlashAttribute("info", "El servicio ya existe en este contrato de alquiler");
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			redirect.addFlashAttribute("info", "Servicio dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
		
		return "redirect:/alquileres/verServicios/"+idAlquiler;
	}
}
