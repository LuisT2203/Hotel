package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.modelo.Servicio;

import com.hotel.demo.service.ServicioService;
@RestController
@RequestMapping(value = "servicio", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorServicio {
	@Autowired
	private ServicioService service;
	
	@GetMapping
	public List<Servicio> listarServicioes() {
		return service.listarServicio();
		
	}
	@GetMapping("/{Id_servicio}")
	public Servicio editar(@PathVariable ("Id_servicio")  int Id_servicio) {
		return service.listarId(Id_servicio);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Servicio insertarServicio(@RequestBody Servicio s) {
		return service.Guardar(s);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Servicio actualizarServicio(@RequestBody Servicio s ) {
		return service.Guardar(s);
		
	}
	
	@DeleteMapping("/{Id_servicio}")
	public Servicio eliminar(@PathVariable ("Id_servicio")  int Id_servicio) {
		return service.Borrar(Id_servicio);
		
	}
}
