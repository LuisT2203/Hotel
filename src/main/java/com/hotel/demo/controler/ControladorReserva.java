package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.hotel.demo.DTOS.ReservaDTO;
import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.HuespedService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "reserva", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorReserva {
	@Autowired
	private ReservaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listarReservas() {
		List<Reserva> lista = service.listarReserva();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("No hay registros").object(null).build(),
					HttpStatus.OK
			);
		} else {
			List<ReservaDTO> listaDTO = lista.stream()
					.map(r -> mapper.map(r, ReservaDTO.class)) // Asegúrate de tener un DTO para Reserva
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Si hay registros").object(listaDTO).build(),
					HttpStatus.OK
			);
		}
	}
	@GetMapping("/{nro_reserva}")
	public ResponseEntity<?> obtenerReserva(@PathVariable("nro_reserva") int nro_reserva) {
		Reserva reserva = service.listarNro(nro_reserva);
		if (reserva == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Reserva no encontrada").object(null).build(),
					HttpStatus.NOT_FOUND
			);
		}
		ReservaDTO reservaDTO = mapper.map(reserva, ReservaDTO.class);
		return new ResponseEntity<>(reservaDTO, HttpStatus.OK);
	}
	@PostMapping("/registrar")
	public ResponseEntity<?> insertarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
		try {
			Reserva reserva = mapper.map(reservaDTO, Reserva.class);
			Reserva nuevaReserva = service.crearReservaConDetalle(reserva);
			ReservaDTO responseDTO = mapper.map(nuevaReserva, ReservaDTO.class);
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Guardado correctamente").object(responseDTO).build(),
					HttpStatus.CREATED
			);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al guardar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
		try {
			Reserva reserva = mapper.map(reservaDTO, Reserva.class);
			Reserva actualizada = service.crearReservaConDetalle(reserva);
			ReservaDTO responseDTO = mapper.map(actualizada, ReservaDTO.class);
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Actualizado correctamente").object(responseDTO).build(),
					HttpStatus.OK
			);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al actualizar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	@DeleteMapping("/{nro_reserva}")
	public ResponseEntity<?> eliminarReserva(@PathVariable("nro_reserva") int nro_reserva) {
		try {
			Reserva eliminado = service.Borrar(nro_reserva);
			if (eliminado == null) {
				return new ResponseEntity<>(
						MensajeResponse.builder().mensaje("Reserva no encontrada").object(null).build(),
						HttpStatus.NOT_FOUND
				);
			}
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(),
					HttpStatus.OK
			);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	

}
