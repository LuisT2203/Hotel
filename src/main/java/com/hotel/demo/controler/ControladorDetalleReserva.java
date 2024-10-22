package com.hotel.demo.controler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.hotel.demo.DTOS.Detalle_ReservaDTO;
import com.hotel.demo.modelo.Detalle_Reserva;

import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.utils.MensajeResponse;
import com.hotel.demo.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

import com.hotel.demo.service.DetalleReservaService;





@RestController
@RequestMapping(value = "detallereserva", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorDetalleReserva {
	@Autowired
	private DetalleReservaService serviceDR;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaDetalleReserva() throws Exception{
		try {
			List<Detalle_Reserva> lista = serviceDR.listarDetReserva();
			if(lista.size() == 0) {
				return new ResponseEntity<>(
						MensajeResponse.builder()
						.mensaje("No hay Detalle de Reserva")
						.object(null)
						.build(), HttpStatus.NO_CONTENT);
			} else {
				List<Detalle_ReservaDTO> lista2 = lista.stream()
						.map(m -> mapper.map(m, Detalle_ReservaDTO.class))
						.collect(Collectors.toList());
				return new ResponseEntity<> (
						MensajeResponse.builder()
						.mensaje("Si hay registro de DetalleReserva")
						.object(lista2)
						.build(), HttpStatus.OK);
			}
		} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	/*
	@GetMapping("/{Id_detreserva}")
	public Detalle_Reserva editar(@PathVariable ("Id_detreserva")  int Id_detreserva) {
		return serviceDR.listarId(Id_detreserva);
		
	}
	*/
	
	@PostMapping("/insertar")
	public ResponseEntity<?> insertarDetalleReserva(@Valid @RequestBody Detalle_ReservaDTO bean) throws Exception{
		try {
			Detalle_Reserva det = mapper.map(bean, Detalle_Reserva.class);
			Detalle_Reserva detr = serviceDR.GuardarDR(det);
			Detalle_ReservaDTO d = mapper.map(detr, Detalle_ReservaDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se agrego correctamente la habitacion")
					.object(d).build(),HttpStatus.CREATED);
		} catch (DataException e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarDetalleReserva(@PathVariable int id, @RequestBody Detalle_Reserva detalleReserva) throws Exception{
	    try {
	    	detalleReserva.setId_detareser(id); 
	    	Detalle_Reserva actualizado = serviceDR.GuardarDR(detalleReserva);
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
				Detalle_Reserva det = serviceDR.listarId(id);
				if(det == null)
					throw new ModeloNotFoundException("ID NO ECONTRADO : "+id);
				else{
					serviceDR.BorrarYDisponibilizar(id);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
	
}
