package com.hotel.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IDetalleReserva;



import com.hotel.demo.modelo.Detalle_Reserva;
import com.hotel.demo.modelo.Habitacion;
import com.hotel.demo.modelo.Reserva;

import jakarta.transaction.Transactional;

@Service
public class DetalleReservaService {
	@Autowired
	private IDetalleReserva data;
	@Autowired
	private HabitacionService ServiceH;
	
	
	public List<Detalle_Reserva> listarDetReserva() {
		return (List<Detalle_Reserva>)data.findAll();
	}

	
	public Detalle_Reserva listarId(int Id_detreserva) {
		return data.findById(Id_detreserva).orElse(new Detalle_Reserva());
	}

	
	public int Guardar(Detalle_Reserva dr) {
		int res=0;
		Detalle_Reserva detallereserva=data.save(dr);
		if (!detallereserva.equals(null)) {
			res=1;
		}
		return res;
	}

	
	public Detalle_Reserva BorrarYDisponibilizar(int Id_detreserva) {
		 Detalle_Reserva detreserva =data.findById(Id_detreserva).orElse(null);
	        if (detreserva !=null) {
	            // Obtener la reserva asociada al detalle
	            Reserva reserva = detreserva.getReserva();

	            // Verificar si la reserva y la habitación asociada existen
	            if (reserva != null && reserva.getHabitacion() != null) {
	                // Obtener la habitación y actualizar su estado
	                Habitacion habitacion = reserva.getHabitacion();
	                habitacion.disponibilizar(); // Utiliza el método que actualiza el estado
	                ServiceH.Guardar(habitacion); // Guardar la habitación
	            }

	            // Eliminar el detalle de la reserva
	            data.deleteById(Id_detreserva);
	            return detreserva;
	        }
			return detreserva;
	}
	
	public Detalle_Reserva GuardarDR(Detalle_Reserva dr) {
		    // Establecer un valor predeterminado si id_emp es null
		    
		Detalle_Reserva detreserva = data.save(dr);
		    return detreserva;
		}
	
	@Transactional
	public Detalle_Reserva crearReservaConDetalle(Detalle_Reserva detreserva) {
	    // Obtener la habitación por ID
	    Habitacion habitacion = ServiceH.listarNro(detreserva.getHabitacion().getNro_habi());

	    if (habitacion != null) {
	        // Guardar la reserva
	    	detreserva = GuardarDR(detreserva);

	        // Configurar el objeto Detalle_Reserva
	        
	        
	        detreserva.setHabitacion(habitacion);
	        // Configurar otros campos según sea necesario

	        // Guardar el detalle de la reserva
	        Guardar(detreserva);

	        // Actualizar el estado de la habitación
	        habitacion.reservar(); // Utiliza el método que actualiza el estado
	        ServiceH.Guardar(habitacion); // Guarda la habitación actualizada

	        return detreserva;
	    } else {
	        throw new RuntimeException("La Habitación asociada a la reserva no existe");
	    }
	}

	
}
