package com.edix.Proyecto_Final_Curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.Proyecto_Final_Curso.entities.ProveedoresServicio;
import com.edix.Proyecto_Final_Curso.entities.TiposServicio;
import com.edix.Proyecto_Final_Curso.modeloDao.Proveedores_servicioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_servicioDao;

@RequestMapping("/proveedores")
@Controller
public class ProveedoresController {

	@Autowired
	private Proveedores_servicioDao psDao;
	@Autowired
	private Tipo_servicioDao tsDao;
	
	@PostMapping("/altaNuevo")
	public String altaProveedor(@RequestParam String idServicio,
									@RequestParam String nombre,								
									Model model, RedirectAttributes redirect
			) {
		
		TiposServicio servicio = tsDao.buscarTipoServicio(Integer.parseInt(idServicio));
		
		ProveedoresServicio nuevoProveedor= new ProveedoresServicio(0, nombre, servicio );
		
		if(psDao.altaProveedor(nuevoProveedor)==null) {
			redirect.addFlashAttribute("info", "El proveedor ya existe en nuestra plataforma");		
			redirect.addFlashAttribute("tipo", "danger");
		}else {
			redirect.addFlashAttribute("info", "Proveedor dado de alta correctamente");
			redirect.addFlashAttribute("tipo", "success");
		}
			
		return "redirect:/servicios/modulo";
	}
}
