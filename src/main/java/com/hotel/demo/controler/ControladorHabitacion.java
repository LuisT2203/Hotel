package com.hotel.demo.controler;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.DTOS.HabitacionDTO;
import com.hotel.demo.DTOS.ServicioDTO;
import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.utils.MensajeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "habitacion", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorHabitacion {
	@Autowired
	HabitacionService service;
	
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/lista")
	public ResponseEntity<?> listaHabitaciones() throws Exception {
		try {
			List<Habitacion> lista = service.listar();
			if(lista.size() == 0) {
				return new ResponseEntity<>(
						MensajeResponse.builder()
						.mensaje("No hay habitaciones")
						.object(null)
						.build(), HttpStatus.NO_CONTENT);
			} else {
				List<HabitacionDTO> lista2 = lista.stream()
						.map(m -> mapper.map(m, HabitacionDTO.class))
						.collect(Collectors.toList());
				return new ResponseEntity<> (
						MensajeResponse.builder()
						.mensaje("Si hay registro de Habitaciones")
						.object(lista2)
						.build(), HttpStatus.OK);
			}

		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{Nro_habi}")
    public ResponseEntity<?> obtenerServicio(@PathVariable("Nro_habi") int Nro_habi) {
        Habitacion habitacion = service.listarNro(Nro_habi);
        if (habitacion == null) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Habitacion no encontrada").object(null).build(), HttpStatus.NOT_FOUND);
        }
        ServicioDTO servicioDTO = mapper.map(habitacion, ServicioDTO.class);
        return new ResponseEntity<>(servicioDTO, HttpStatus.OK);
    }


	@PostMapping("/registrar")
	public ResponseEntity<?> insertarHabitacion(@Valid @RequestBody HabitacionDTO bean) throws Exception{
		try {
			Habitacion hab = mapper.map(bean, Habitacion.class);
			Habitacion habi = service.Guardar(hab);
			HabitacionDTO h = mapper.map(habi, HabitacionDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se agrego correctamente la habitacion")
					.object(h).build(),HttpStatus.CREATED);
		} catch (DataException e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarHabitacion(@Valid @RequestBody HabitacionDTO bean) throws Exception{
		try {
			Habitacion hab = mapper.map(bean, Habitacion.class);
			Habitacion habi = service.Guardar(hab);
			HabitacionDTO h = mapper.map(habi, HabitacionDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se Actualizó correctamente la habitacion")
					.object(h).build(),HttpStatus.CREATED);
		} catch (DataException e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo eliminar por id [faltaria agregar un mensaje de cofirmacion de que se elimino correctamente]
		@DeleteMapping("/{Nro_habi}")
		public ResponseEntity<?> eliminar(@PathVariable("Nro_habi") int Nro_habi) {
	        try {
	            Habitacion eliminado = service.Borrar(Nro_habi);
	            if (eliminado == null) {
	                return new ResponseEntity<>(MensajeResponse.builder().mensaje("Habitacion no encontrada").object(null).build(), HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(), HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
