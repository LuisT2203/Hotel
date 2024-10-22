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
import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.utils.MensajeResponse;
import com.hotel.demo.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "habitaciones", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorHabitacion {
	
	@Autowired
	HabitacionService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaHabitaciones() throws Exception {
		try {
			List<Habitacion> lista = service.listarHabitacion();
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
	
	/* 
	@GetMapping("/{Nro_habi}")
	public Habitacion editar(@PathVariable ("Nro_habi")  int Nro_habi) {
		return service.listarNro(Nro_habi);
		
	}
	*/ 
	
	
	@PostMapping("/insertar")
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
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarHabitacion(@PathVariable int id, @RequestBody Habitacion habitacion) throws Exception{
	    try {
	    	habitacion.setNro_habi(id); 
	    	Habitacion actualizado = service.Guardar(habitacion);
	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mensaje("Actualizado correctamente")
	                .object(actualizado)
	                .build(), HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mensaje("Error al actualizar")
	                .object(null)
	                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// Metodo eliminar por id [faltaria agregar un mensaje de cofirmacion de que se elimino correctamente]
		@DeleteMapping("/eliminar/{id}")
		public ResponseEntity<Void> eliminarHabitacion(@PathVariable Integer id) throws Exception{
			Habitacion hab = service.listarNro(id);
			if(hab == null)
				throw new ModeloNotFoundException("ID NO ECONTRADO : "+id);
			else{
				service.Borrar(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
}
