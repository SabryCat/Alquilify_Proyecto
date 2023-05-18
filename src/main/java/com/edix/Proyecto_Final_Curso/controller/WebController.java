package com.edix.Proyecto_Final_Curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edix.Proyecto_Final_Curso.entities.TiposUsuario;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_usuarioDao;

/**
 * 
 * Controller para las páginas de la web
 * 
 * @author sabrycat
 * @version 1.0
 *
 */

@Controller
public class WebController {
	
	@Autowired
	private Tipo_usuarioDao tudao;
	
	/**
	 * ruta a la página de contacto
	 * 
	 * @return web html
	 */
	@GetMapping("/contacto")
	public String contacto() {						
		return "contacto";		 		
	}	
	/**
	 * ruta a la aplicación con
	 * usuario demo
	 * 
	 * @return web html
	 */	
	@GetMapping("/demo")
	public String demo() {						
		return "app/demo";		 		
	}
	/**
	 * ruta a la página de nosotros
	 * 
	 * @return web html
	 */	
	@GetMapping("/nosotros")
	public String nosotros() {						
		return "nosotros";		 		
	}	
	/**
	 * ruta a la página cuenta de usuario
	 * y acceso a la aplicación
	 * 
	 * @return web html
	 */	
	@GetMapping("/cuenta")
	public String cuenta(Model model) {
		
		List<TiposUsuario> tipousuario = tudao.buscarTodos(); 
		model.addAttribute("tipousuario",tipousuario);
		return "cuenta";		 		
	}
	/**
	 * ruta a la página de servicios
	 * 
	 * @return web html
	 */		
	@GetMapping("/servicios")
	public String servicios() {						
		return "servicios";		 		
	}
	/**
	 * ruta a la home
	 * 
	 * @return web html
	 */		
	@GetMapping("/")
	public String home() {						
		return "index";		 		
	}
	
}
