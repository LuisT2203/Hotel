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
import com.hotel.demo.DTOS.ServicioDTO;
import com.hotel.demo.modelo.Detalle_Reserva;
import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.utils.MensajeResponse;

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
	@GetMapping("/{Id_detreserva}")
	public ResponseEntity<?> obtenerDR(@PathVariable("Id_detreserva") int Id_detreserva) {
        
            Detalle_Reserva eliminado = serviceDR.listarId(Id_detreserva);
            if (eliminado == null) {
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("Servicio no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
            }
            Detalle_ReservaDTO dr = mapper.map(eliminado, Detalle_ReservaDTO.class);
            return new ResponseEntity<>(dr, HttpStatus.OK);   
	}
	
	@PostMapping("/registrar")
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
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarDetalleReserva(@Valid @RequestBody Detalle_ReservaDTO bean) throws Exception{
		try {
			Detalle_Reserva det = mapper.map(bean, Detalle_Reserva.class);
			Detalle_Reserva detr = serviceDR.GuardarDR(det);
			Detalle_ReservaDTO d = mapper.map(detr, Detalle_ReservaDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se Actualizó Correctamente  el Detalle Reserva")
					.object(d).build(),HttpStatus.CREATED);
		} catch (DataException e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id_servicio}")
    public ResponseEntity<?> eliminar(@PathVariable("Id_detreserva") int Id_detreserva) {
        try {
            Detalle_Reserva eliminado = serviceDR.BorrarYDisponibilizar(Id_detreserva);
            if (eliminado == null) {
                return new ResponseEntity<>(MensajeResponse.builder().mensaje(" Detalle Reserva  no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
