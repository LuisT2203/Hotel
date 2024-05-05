package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.hotel.demo.interfacesService.IempleadoService;
import com.hotel.demo.interfacesService.IhabitacionService;
import com.hotel.demo.interfacesService.IhuespedService;
import com.hotel.demo.interfacesService.IreservaService;
import com.hotel.demo.interfacesService.IservicioService;

import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;



@Controller
@RequestMapping
public class ControladorReserva {
	@Autowired
	private IreservaService service;
	@Autowired
	private IservicioService serviceS;
	@Autowired
	private IempleadoService serviceE;
	@Autowired
	private IhabitacionService serviceHa;
	@Autowired
	private IhuespedService serviceHu;
	
	
	@GetMapping("/listarReserva")
	public String listar(Model model) {
		List<Reserva>reservas = service.listarReserva();
		model.addAttribute("reservas",reservas);
		return "Reserva";
	}
	@GetMapping("/newReserva")
	public String agregar(Model model) {
		List<Habitacion>habitaciones = serviceHa.listar();
		List<Huesped>huespeds = serviceHu.listarHuesped();
		List<Empleado>empleados = serviceE.listarEmpleado();		
		List<Servicio>servicios = serviceS.listarServicio();
		model.addAttribute("habitaciones",habitaciones);
		model.addAttribute("huespeds",huespeds);
		model.addAttribute("empleados",empleados);	
		model.addAttribute("servicios", servicios);
		model.addAttribute("reserva",new Reserva());
		
		return "NuevaReserva";
	}
	@GetMapping("/newReservaRCliente")
	public String agregarRCliente(Model model) {
		List<Habitacion>habitaciones = serviceHa.listar();
		List<Huesped>huespeds = serviceHu.listarHuesped();
			
		List<Servicio>servicios = serviceS.listarServicio();
		model.addAttribute("habitaciones",habitaciones);
		model.addAttribute("huespeds",huespeds);
	
		model.addAttribute("servicios", servicios);
		model.addAttribute("reserva",new Reserva());
		
		return "NuevaReservaCliente";
	}
	@PostMapping("/saveReserva")
	public String guardar(@Validated Reserva r, Model model) {   
		
	    service.crearReservaConDetalle(r);
	    return "redirect:/listarReserva";
	}
	@GetMapping("/editarReserva/{Nro_reserva}")
	public String editar(@PathVariable int Nro_reserva, Model model) {
		Optional<Reserva>reserva=service.listarNro(Nro_reserva);
		List<Habitacion>habitaciones = serviceHa.listar();
		List<Huesped>huespeds = serviceHu.listarHuesped();
		List<Empleado>empleados = serviceE.listarEmpleado();		
		List<Servicio>servicios = serviceS.listarServicio();
		model.addAttribute("habitaciones",habitaciones);
		model.addAttribute("huespeds",huespeds);
		model.addAttribute("empleados",empleados);	
		model.addAttribute("servicios", servicios);
		model.addAttribute("reserva", reserva);
		return "NuevaReserva";
	}
	@GetMapping("/eliminarReserva/{Nro_reserva}")
	public String delete(Model model, @PathVariable int Nro_reserva) {
		service.Borrar(Nro_reserva);
		return "redirect:/listarReserva";
	}
	
	

}
