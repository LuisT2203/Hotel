package com.hotel.demo.controler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.service.HuespedService;

@RestController
@RequestMapping(value = "huesped", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorHuesped {
	@Autowired
	HuespedService service;
	
	@GetMapping
	public List<Huesped> listarHuespedes() {
		return service.listarHuesped();
		
	}
	@GetMapping("/{Id_huesped}")
	public Huesped editar(@PathVariable ("Id_huesped")  int Id_huesped) {
		return service.listarId(Id_huesped);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Huesped insertarHuesped(@RequestBody Huesped h) {
		return service.Guardar(h);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Huesped actualizarHuesped(@RequestBody Huesped h) {
		return service.Guardar(h);
		
	}
	
	@DeleteMapping("/{Id_huesped}")
	public Huesped eliminar(@PathVariable ("Id_huesped")  int Id_huesped) {
		return service.Borrar(Id_huesped);
		
	}
}
