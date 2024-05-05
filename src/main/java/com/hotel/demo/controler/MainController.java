package com.hotel.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/index1")
	public String index1 (){
		
		return "index1";
	}
	@GetMapping("/index")
	public String index (){
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
