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


import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.service.HabitacionService;

@RestController
@RequestMapping(value = "habitacion", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorHabitacion {
	@Autowired
	HabitacionService service;
	
	
	@GetMapping
	public List<Habitacion> listarHabitaciones() {
		return service.listar();
		
	}
	@GetMapping("/{Nro_habi}")
	public Habitacion editar(@PathVariable ("Nro_habi")  int Nro_habi) {
		return service.listarNro(Nro_habi);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Habitacion insertarHabitacion(@RequestBody Habitacion h) {
		return service.Guardar(h);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Habitacion actualizarHabitacion(@RequestBody Habitacion h) {
		return service.Guardar(h);
		
	}
	
	@DeleteMapping("/{Nro_habi}")
	public Habitacion eliminar(@PathVariable ("Nro_habi")  int Nro_habi) {
		return service.Borrar(Nro_habi);
		
	}
}
