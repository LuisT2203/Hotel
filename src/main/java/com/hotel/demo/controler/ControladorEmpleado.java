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

import com.hotel.demo.service.EmpleadoService;

import com.hotel.demo.modelo.Empleado;

@RestController
@RequestMapping(value = "empleado", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorEmpleado {
	@Autowired
	EmpleadoService service;
	
	@GetMapping
	public List<Empleado> listarEmpleados() {
		return service.listarEmpleado();
	
	}
	@GetMapping("/{Id_emp}")
	public Empleado editar(@PathVariable ("Id_emp")  Integer Id_emp) {
		return service.listarId(Id_emp);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Empleado insertarEmpleado(@RequestBody Empleado e) {
		return service.Guardar(e);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Empleado actualizarEmpleado(@RequestBody Empleado e) {
		return service.Guardar(e);
		
	}
	
	@DeleteMapping("/{Id_emp}")
	public Empleado eliminar(@PathVariable ("Id_emp")  Integer Id_emp) {
		return service.Borrar(Id_emp);
		
	}

}
