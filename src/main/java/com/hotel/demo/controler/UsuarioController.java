package com.hotel.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.demo.modelo.Usuario;
import com.hotel.demo.service.UsuarioService;

@RequestMapping("usuario")
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	public String usuarios() {
		
		return "usuarios";
	}

	@GetMapping("acceder")
	public String acceder() {
		return "login";
	}
	
	@GetMapping("registro")
	public String registroForm (Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("registro")
	public String registroGuardar(Usuario usuario) {
		BCryptPasswordEncoder encoder 
					= new BCryptPasswordEncoder();
		String claveEncriptada
					= encoder.encode(usuario.getClave());
		usuario.setClave(claveEncriptada);
		System.out.println(usuario);
		service.usuarioIns(usuario);
		return "index";
	}
	
	
	
}
