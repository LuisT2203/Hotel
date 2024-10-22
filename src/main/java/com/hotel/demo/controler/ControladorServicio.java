package com.hotel.demo.controler;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.hotel.demo.DTOS.ServicioDTO;
import com.hotel.demo.modelo.Servicio;

import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;
import com.hotel.demo.utils.ModeloNotFoundException;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "servicios", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorServicio {
	@Autowired
	private ServicioService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaServicios() {
		try {
			List<Servicio> lista = service.listarServicio();
			if(lista.size() == 0) {
				return new ResponseEntity<>(
						MensajeResponse.builder()
						.mensaje("No hay registros")
						.object(null)
						.build(), HttpStatus.OK);
			} else {
				List<ServicioDTO> lista2 = lista.stream()
						.map(m -> mapper.map(m, ServicioDTO.class))
						.collect(Collectors.toList());
				return new ResponseEntity<> (
						MensajeResponse.builder()
						.mensaje("si hay registro")
						.object(lista2)
						.build(), HttpStatus.OK);
			}
			
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@GetMapping("/{Id_servicio}")
	public Servicio editar(@PathVariable ("Id_servicio")  int Id_servicio) {
		return service.listarId(Id_servicio);
		
	}
	*/
	
	@PostMapping("/insertar")
	public ResponseEntity<?> insertarServicio(@Valid @RequestBody ServicioDTO bean) throws Exception {
		try {
			Servicio ser = mapper.map(bean, Servicio.class);
			Servicio serv = service.Guardar(ser);
			ServicioDTO s = mapper.map(serv, ServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se agrego correctamente el servicio")
					.object(s).build(),HttpStatus.CREATED);
			} catch (DataAccessException e) {
				return new ResponseEntity<>(MensajeResponse.builder().
						mensaje(e.getMessage()).object(null).build(),
						HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarServicio(@PathVariable int id, @RequestBody Servicio servicio) {
	    try {
	        servicio.setId_servicio(id); 
	        Servicio actualizado = service.Guardar(servicio);
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
	public ResponseEntity<Void> eliminarServicio(@PathVariable Integer id) throws Exception{
		Servicio ser= service.listarId(id);
		if(ser == null)
			throw new ModeloNotFoundException("ID NO ECONTRADO : "+id);
		else{
			service.Borrar(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
		
	}
