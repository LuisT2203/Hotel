package com.hotel.demo.controler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;

@RestController
@RequestMapping(value = "servicio", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorServicio {

	@Autowired
	private ServicioService service;

	@GetMapping("/lista")
	public ResponseEntity<?> listarServicios() {
		List<Servicio> lista = service.listarServicio();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("No hay registros").object(null).build(),
					HttpStatus.OK
			);
		} else {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Si hay registros").object(lista).build(),
					HttpStatus.OK
			);
		}
	}

	@GetMapping("/{id_servicio}")
	public ResponseEntity<?> editar(@PathVariable("id_servicio") int id_servicio) {
		Servicio servicio = service.listarId(id_servicio);
		if (servicio == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Servicio no encontrado").object(null).build(),
					HttpStatus.NOT_FOUND
			);
		}
		return new ResponseEntity<>(servicio, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> insertarServicio(@RequestBody Servicio servicio) {
		try {
			Servicio nuevoServicio = service.Guardar(servicio);
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Guardado correctamente").object(nuevoServicio).build(),
					HttpStatus.CREATED
			);
		} catch (Exception e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al guardar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarServicio(@RequestBody Servicio servicio) {
		try {
			Servicio actualizado = service.Guardar(servicio);
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Actualizado correctamente").object(actualizado).build(),
					HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al actualizar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

	@DeleteMapping("/{id_servicio}")
	public ResponseEntity<?> eliminar(@PathVariable("id_servicio") int id_servicio) {
		try {
			Servicio eliminado = service.Borrar(id_servicio);
			if (eliminado == null) {
				return new ResponseEntity<>(
						MensajeResponse.builder().mensaje("Servicio no encontrado").object(null).build(),
						HttpStatus.NOT_FOUND
				);
			}
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(),
					HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
}
