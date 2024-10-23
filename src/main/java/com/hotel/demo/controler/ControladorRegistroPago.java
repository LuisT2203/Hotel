
package com.hotel.demo.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.DTOS.Registro_PagoDTO;

import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Registro_Pago;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.RegistroPagoService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;

import jakarta.validation.Valid;

@RestController

@RequestMapping(value = "pago", produces = MediaType.APPLICATION_JSON_VALUE)

@CrossOrigin(origins = "*")
public class ControladorRegistroPago {

	@Autowired
	private RegistroPagoService service;

	@Autowired
	private ModelMapper mapper;
	@GetMapping("/lista")
	public ResponseEntity<?> listarRegPagos() {
		List<Registro_Pago> lista = service.listarRegPago();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("No hay registros").object(null).build(), HttpStatus.OK);
		} else {
			List<Registro_PagoDTO> listaDTO = lista.stream()
					.map(rp -> mapper.map(rp, Registro_PagoDTO.class)) // Asegúrate de tener un DTO para Registro_Pago
					.collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Si hay registros").object(listaDTO).build(), HttpStatus.OK);
		}
	}

	@GetMapping("/{Id_pago}")
	public ResponseEntity<?> obtenerRegistroPago(@PathVariable("Id_pago") Integer Id_pago) {
		Registro_Pago registroPago = service.listarId(Id_pago);
		if (registroPago == null) {
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Registro de pago no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
		}
		Registro_PagoDTO registroPagoDTO = mapper.map(registroPago, Registro_PagoDTO.class);
		return new ResponseEntity<>(registroPagoDTO, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> insertarRegistroPago(@Valid @RequestBody Registro_PagoDTO registroPagoDTO) {
		try {
			Registro_Pago registroPago = mapper.map(registroPagoDTO, Registro_Pago.class);
			Registro_Pago nuevoRegistroPago = service.GuardarRegistro(registroPago);
			Registro_PagoDTO responseDTO = mapper.map(nuevoRegistroPago, Registro_PagoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Guardado correctamente").object(responseDTO).build(), HttpStatus.CREATED);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al guardar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarRegistroPago(@Valid @RequestBody Registro_PagoDTO registroPagoDTO) {
		try {
			Registro_Pago registroPago = mapper.map(registroPagoDTO, Registro_Pago.class);
			Registro_Pago actualizado = service.GuardarRegistro(registroPago);
			Registro_PagoDTO responseDTO = mapper.map(actualizado, Registro_PagoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Actualizado correctamente").object(responseDTO).build(), HttpStatus.OK);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al actualizar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{Id_pago}")
	public ResponseEntity<?> eliminarRegistroPago(@PathVariable("Id_pago") Integer Id_pago) {
		try {
			Registro_Pago eliminado = service.Borrar(Id_pago);
			if (eliminado == null) {
				return new ResponseEntity<>(MensajeResponse.builder().mensaje("Registro de pago no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(), HttpStatus.OK);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
 
