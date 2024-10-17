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


import com.hotel.demo.modelo.Detalle_Reserva;
import com.hotel.demo.modelo.Habitacion;

import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.DetalleReservaService;





@RestController
@RequestMapping(value = "detallereserva", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorDetalleReserva {
	@Autowired
	private DetalleReservaService serviceDR;
	
	@GetMapping
	public List<Detalle_Reserva> listarDetalle_Reserva() {
		return serviceDR.listarDetReserva();
		
	}
	@GetMapping("/{Id_detreserva}")
	public Detalle_Reserva editar(@PathVariable ("Id_detreserva")  int Id_detreserva) {
		return serviceDR.listarId(Id_detreserva);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Detalle_Reserva insertarDetalle_Reserva(@RequestBody Detalle_Reserva dr) {
		return serviceDR.crearReservaConDetalle(dr);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Detalle_Reserva actualizarDetalle_Reserva(@RequestBody Detalle_Reserva dr) {
		return serviceDR.GuardarDR(dr);
		
	}
	
	@DeleteMapping("/{Id_detreserva}")
	public Detalle_Reserva eliminar(@PathVariable ("Id_detreserva")  int Id_detreserva) {
		return serviceDR.BorrarYDisponibilizar(Id_detreserva);
		
	}
	
}
