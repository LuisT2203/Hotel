package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
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
	private EmpleadoService serviceE;
	@Autowired
	private ServicioService serviceS;
	
	@GetMapping
	public List<Detalle_Servicio> listarDetalle_Servicio() {
		return serviceDS.listarDetServicio();
		
	}
	@GetMapping("/{Id_detservicio}")
	public Detalle_Servicio editar(@PathVariable ("Id_detservicio")  int Id_detservicio) {
		return serviceDS.listarId(Id_detservicio);
		
	}
	
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Detalle_Servicio insertarDetalle_Servicio(@RequestBody Detalle_Servicio ds) {
		return serviceDS.Guardar(ds);
		
	}
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public Detalle_Servicio actualizarDetalle_Servicio(@RequestBody Detalle_Servicio ds) {
		return serviceDS.Guardar(ds);
		
	}
	
	@DeleteMapping("/{Id_detservicio}")
	public Detalle_Servicio eliminar(@PathVariable ("Id_detservicio")  int Id_detservicio) {
		return serviceDS.Borrar(Id_detservicio);
		
	}
}
