package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

import com.hotel.demo.service.DetalleServicioService;
import com.hotel.demo.service.EmpleadoService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;
import com.hotel.demo.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

import com.hotel.demo.DTOS.Detalle_ServicioDTO;
import com.hotel.demo.modelo.Detalle_Reserva;
import com.hotel.demo.modelo.Detalle_Servicio;



@RestController
@RequestMapping(value = "detalleservicio", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorDetalleServicio {
	@Autowired
	private DetalleServicioService serviceDS;
	@Autowired
	@Lazy
	private ReservaService serviceR;
	
	@Autowired
	private ModelMapper mapper;

	
	@GetMapping("/lista")
	public ResponseEntity<?> listaDetalleServicio() throws Exception{
		try {
			List<Detalle_Servicio> lista = serviceDS.listarDetServicio();
			if(lista.size() == 0) {
				return new ResponseEntity<>(
						MensajeResponse.builder()
						.mensaje("No hay Detalle Servicio")
						.object(null)
						.build(), HttpStatus.NO_CONTENT);
			} else {
				List<Detalle_ServicioDTO> lista2 = lista.stream()
						.map(m -> mapper.map(m, Detalle_ServicioDTO.class))
						.collect(Collectors.toList());
				return new ResponseEntity<> (
						MensajeResponse.builder()
						.mensaje("Si hay registro de Detalle Servicio")
						.object(lista2)
						.build(), HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@GetMapping("/{Id_detservicio}")
	public Detalle_Servicio editar(@PathVariable ("Id_detservicio")  int Id_detservicio) {
		return serviceDS.listarId(Id_detservicio);
		
	}
	*/
	
	@PostMapping("/insertar")
	public ResponseEntity<?> insertarDetalleServicio(@Valid @RequestBody Detalle_ServicioDTO bean) throws Exception{
		try {
			Detalle_Servicio des = mapper.map(bean, Detalle_Servicio.class);
			Detalle_Servicio dese = serviceDS.Guardar(des);
			Detalle_ServicioDTO d = mapper.map(dese, Detalle_ServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se agrego correctamente el Detalle Servicio")
					.object(d).build(),HttpStatus.CREATED);
		} catch (DataException e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarDetalleServicio(@PathVariable int id, @RequestBody Detalle_Servicio detalleServicio) throws Exception{
		try {
			detalleServicio.setId_detaserv(id);
			Detalle_Servicio actualizado = serviceDS.Guardar(detalleServicio);
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
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminarDetalleServicio(@PathVariable Integer id) throws Exception{
		Detalle_Servicio det = serviceDS.listarId(id);
		if(det == null)
			throw new ModeloNotFoundException("ID NO ECONTRADO : "+id);
		else{
			serviceDS.Borrar(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
