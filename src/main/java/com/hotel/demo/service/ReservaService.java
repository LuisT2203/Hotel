package com.hotel.demo.service;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.interfaces.IReserva;



import com.hotel.demo.modelo.Detalle_Servicio;

import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;

import jakarta.transaction.Transactional;
@Service
public class ReservaService{
	@Autowired
	private IReserva data;
	
	@Autowired
	private EmpleadoService serviceE;
	
	@Autowired
	private ServicioService serviceS;
	@Autowired
	private DetalleServicioService serviceDS;
	
	public List<Reserva> listarReserva() {
		return (List<Reserva>)data.findAll();
	}

	
	public Reserva listarNro(int nro_reserva) {
		return data.findById(nro_reserva).orElse(null);
	}

	

	
	public Reserva Borrar(int nro_reserva) {
		Reserva temp = data.findById(nro_reserva).orElse(null);
		if(temp==null) {
			return new Reserva();
		}else {
			data.deleteById(nro_reserva);
			return temp;
		}
	}

	
	public Reserva GuardarR(Reserva r) {
	    // Establecer un valor predeterminado si id_emp es null
	    if (r.getServicio().getId_servicio() == 1) {
	        r.getEmpleado().setId_emp(1);
	    }

	    Reserva reserva = data.save(r);
	    return reserva;
	}

	 @Transactional
	    public Reserva crearReservaConDetalle(Reserva reserva) {
	        // Obtener el servicio por ID
	        Servicio servicio = serviceS.listarId(reserva.getServicio().getId_servicio());
	        Empleado empleado = serviceE.listarId(reserva.getEmpleado().getId_emp());

	        if (servicio != null) {
	        	
	            // Guardar la reserva
	            reserva = GuardarR(reserva);

	            // Crear y configurar el objeto Detalle_Servicio
	            
	            Detalle_Servicio detalleServicio = new Detalle_Servicio();
	            detalleServicio.setReserva(reserva);
	            detalleServicio.setServicio(servicio);
	            detalleServicio.setEmpleado(empleado);

	            
	            detalleServicio.setHora_serv(new Time(Calendar.getInstance().getTimeInMillis()));
	            detalleServicio.setEstado_serv("No realizado"); // Estado por defecto
	            
	            

	            // Guardar el detalle del servicio
	            serviceDS.Guardar(detalleServicio);

	            return reserva;
	        } else {
	            throw new RuntimeException("El Servicio asociado a la reserva no existe");
	        }
	    }
	 

		    // Otros métodos

		    public double obtenerPrecioServicioPorId(int idServicio) {
		        Servicio servicioOptional = serviceS.listarId(idServicio);
		        return servicioOptional.getPrecio();// .map(Servicio::getPrecio).orElse(0.0); // Cambiado a 0.0 para que sea un valor double
		    }

		    // Otros métodos
		
	
}
