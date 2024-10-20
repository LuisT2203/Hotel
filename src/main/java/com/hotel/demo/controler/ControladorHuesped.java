package com.hotel.demo.controler;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.DTOS.HuespedDTO;
import com.hotel.demo.service.HuespedService;
import com.hotel.demo.utils.MensajeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "huesped", produces = "application/json")
@CrossOrigin(origins = "*")
public class ControladorHuesped {

	@Autowired
	private HuespedService service;

	@Autowired
	private ModelMapper mapper;


	@GetMapping("/lista")
	public ResponseEntity<?> listarHuespedes() {
		List<Huesped> lista = service.listarHuesped();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("No hay registros").object(null).build(),
					HttpStatus.OK
			);
		} else {
			List<HuespedDTO> listaDTO = lista.stream()
					.map(h -> mapper.map(h, HuespedDTO.class))
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Si hay registros").object(listaDTO).build(),
					HttpStatus.OK
			);
		}
	}


	@GetMapping("/{id_huesped}")
	public ResponseEntity<?> obtenerHuesped(@PathVariable("id_huesped") int id_huesped) {
		Huesped huesped = service.listarId(id_huesped);
		if (huesped == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Huesped no encontrado").object(null).build(),
					HttpStatus.NOT_FOUND
			);
		}
		HuespedDTO huespedDTO = mapper.map(huesped, HuespedDTO.class);
		return new ResponseEntity<>(huespedDTO, HttpStatus.OK);
	}
	@PostMapping("/registrar")
	public ResponseEntity<?> insertarHuesped(@Valid @RequestBody HuespedDTO huespedDTO) {
		try {
			Huesped huesped = mapper.map(huespedDTO, Huesped.class);
			Huesped nuevoHuesped = service.Guardar(huesped);
			HuespedDTO responseDTO = mapper.map(nuevoHuesped, HuespedDTO.class);
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
	public ResponseEntity<?> actualizarHuesped(@Valid @RequestBody HuespedDTO huespedDTO) {
		try {
			Huesped huesped = mapper.map(huespedDTO, Huesped.class);
			Huesped actualizado = service.Guardar(huesped);
			HuespedDTO responseDTO = mapper.map(actualizado, HuespedDTO.class);
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
	@DeleteMapping("/{id_huesped}")
	public ResponseEntity<?> eliminarHuesped(@PathVariable("id_huesped") int id_huesped) {
		try {
			Huesped eliminado = service.Borrar(id_huesped);
			if (eliminado == null) {
				return new ResponseEntity<>(
						MensajeResponse.builder().mensaje("Huesped no encontrado").object(null).build(),
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
