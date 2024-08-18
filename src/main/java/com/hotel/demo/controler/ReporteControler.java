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



import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.service.HabitacionService;
import com.hotel.demo.service.ReservaService;
import com.hotel.demo.service.ServicioService;

public class ReporteControler {
	@Controller
	public class ReservaPagoController {
		@Autowired
		private ReservaService serviceR;
		@Autowired
		private ServicioService serviceS;
		@Autowired
		private HabitacionService serviceHa;

	    @GetMapping("/reserva_pago")
	    public String mostrarReservaPago(Model model) {
	        // Supongamos que tienes servicios para obtener las listas de entidades
	        List<Reserva> reservas = serviceR.listarReserva();
	        List<Habitacion> habitaciones = serviceHa.listar();
	        List<Servicio> servicios = serviceS.listarServicio();

	        // Agrega las listas al modelo para que puedan ser utilizadas en la vista
	        model.addAttribute("reservas", reservas);
	        model.addAttribute("habitaciones", habitaciones);
	        model.addAttribute("servicios", servicios);

	        return "Reporte";
	    }
	}
}
