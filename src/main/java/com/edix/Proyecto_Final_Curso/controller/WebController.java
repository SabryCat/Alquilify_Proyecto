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
import com.edix.Proyecto_Final_Curso.modeloDao.Proveedores_servicioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.ProvinciasDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_contratoDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_servicioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.Tipo_usuarioDao;
import com.edix.Proyecto_Final_Curso.modeloDao.UsuarioDao;

/**
 * 
 * Controller para las páginas de la web
 * 
 * @author sabrycat
 * @version 1.0
 *
 */
@RequestMapping("/")
@Controller
public class WebController {
	
	@Autowired
	private Tipo_usuarioDao tudao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private Tipo_contratoDao tcdao;
	@Autowired
	private ProvinciasDao pdao;
	@Autowired
	private Tipo_servicioDao tsdao;
	@Autowired
	private Proveedores_servicioDao esdao;
	
	/**
	 * ruta a la página de contacto
	 * 
	 * @return web html
	 */
	@GetMapping("contacto")
	public String contacto() {						
		return "contacto";		 		
	}	
	/**
	 * ruta a la aplicación con
	 * usuario demo
	 * 
	 * @return web html
	 */	
	@GetMapping("demo")
	public String demo() {						
		return "app/demo";		 		
	}
	/**
	 * ruta a la página de nosotros
	 * 
	 * @return web html
	 */	
	@GetMapping("nosotros")
	public String nosotros() {						
		return "nosotros";		 		
	}	
	/**
	 * ruta a la página cuenta de usuario
	 * y acceso a la aplicación
	 * 
	 * @return web html
	 */	
	@GetMapping("cuenta")
	public String cuenta(Model model) {		
		List<TiposUsuario> tipousuario = tudao.buscarTodos(); 
		//System.out.println(tipousuario);
		model.addAttribute("tipousuario",tipousuario);
		
		/*List<TipoContrato> tiposcontratos = tcdao.buscarTodos();
		model.addAttribute("tiposcontratos",tiposcontratos);

		List<Provincia> provincias = pdao.buscarTodas();
		model.addAttribute("provincias", provincias);
		
		List<TiposServicio> tiposservicios = tsdao.buscarTodos();
		model.addAttribute("tiposservicios", tiposservicios);
		
		List<ProveedoresServicio> proveedores = esdao.buscarTodos();
		model.addAttribute("proveedores", proveedores);*/
		return "cuenta";
	
	}
	/**
	 * ruta a la página de servicios
	 * 
	 * @return web html
	 */		
	@GetMapping("servicios")
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

	/**
	 * ruta al panel de la aplicación
	 * 
	 * @return web html
	 */		
	@GetMapping("app/panelControl")
	public String panelControl(Authentication aut, Model model, HttpSession misession) {
		Usuario usuario = udao.buscarByEmail(aut.getName());
		String tipoUsuarioSession = usuario.getTiposUsuario().getTipo();
		misession.setAttribute("idUsuarioSession", usuario.getIdUsuario());
		misession.setAttribute("nombreUsuarioSession", usuario.getNombre());
		misession.setAttribute("tipoUsuarioSession",tipoUsuarioSession );
		return "app/panelControl";		 		
	}
}


