
package com.hotel.demo.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.interfacesService.IregistroPagoService;
import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Registro_Pago;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.RegistroPagoService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;

@RestController

@RequestMapping(value = "pago", produces = MediaType.APPLICATION_JSON_VALUE)

@CrossOrigin(origins = "*")
public class ControladorRegistroPago {

	@Autowired
	private RegistroPagoService service;

	@Autowired
	private ReservaService serviceR;

	@Autowired
	private ServicioService serviceS;

	@GetMapping
	public List<Registro_Pago> listarEmpleados() {
		return service.listarRegPago();

	}

	@GetMapping("/{Id_pago}")
	public Registro_Pago editar(@PathVariable("Id_pago") Integer Id_pago) {
		return service.listarId(Id_pago);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Registro_Pago insertarRegistropago(@RequestBody Registro_Pago r) {
		return service.GuardarRegistro(r);

	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Registro_Pago acyualizarrRegistropago(@RequestBody Registro_Pago r) {
		return service.GuardarRegistro(r);

	}

	@DeleteMapping("/{Id_pago}")
	public Registro_Pago eliminar(@PathVariable("Id_pago") Integer Id_pago) {
		return service.Borrar(Id_pago);

	}

//	@GetMapping
//
//	@ResponseBody
//	public double getPrecioServicio(@RequestParam int nro_reserva) {
//		try { // Agrega la lógica para obtener el precio de servicio directamentede la base de
//				// datos
//			double precioServicio = serviceR.obtenerPrecioServicioPorId(nro_reserva);
//
//			// Si todo está bien, devuelve el precio del servicio return precioServicio;
//		} catch (Exception e) { // Manejo de errores, puedes personalizar esto según tus necesidades
//			e.printStackTrace();
//			return 0.0; // O un valor predeterminadoen caso de error
//		}
//	}

}
 
