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
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.HuespedService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;



@RestController
@RequestMapping(value = "reserva", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorReserva {
	@Autowired
	private ReservaService service;
	@Autowired
	private ServicioService serviceS;
	@Autowired
	private EmpleadoService serviceE;
	@Autowired
	private ReservaService serviceHa;
	@Autowired
	private HuespedService serviceHu;
	
	
	@GetMapping
	public List<Reserva> listarReserva() {
		return service.listarReserva();
		
	}
	@GetMapping("/{nro_reserva}")
	public Reserva editar(@PathVariable ("nro_reserva")  int nro_reserva) {
		return service.listarNro(nro_reserva);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Reserva insertarReserva(@RequestBody Reserva r) {
		return service.crearReservaConDetalle(r);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Reserva actualizarReserva(@RequestBody Reserva r) {
		return service.crearReservaConDetalle(r);
		
	}
	
	@DeleteMapping("/{nro_reserva}")
	public Reserva eliminar(@PathVariable ("nro_reserva")  int nro_reserva) {
		return service.Borrar(nro_reserva);
		
	}
	

}
