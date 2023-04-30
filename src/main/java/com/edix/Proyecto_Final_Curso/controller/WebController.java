package com.edix.Proyecto_Final_Curso.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


	@GetMapping("/contacto")
	public String contacto() {						
		return "contacto";		 		
	}	
	
	@GetMapping("/demo")
	public String demo() {						
		return "app/demo";		 		
	}
	
	@GetMapping("/nosotros")
	public String nosotros() {						
		return "nosotros";		 		
	}	
	
	@GetMapping("/cuenta")
	public String cuenta() {						
		return "cuenta";		 		
	}
	
	@GetMapping("/servicios")
	public String servicios() {						
		return "servicios";		 		
	}
	
	@GetMapping("/")
	public String home() {						
		return "index";		 		
	}
	
}
