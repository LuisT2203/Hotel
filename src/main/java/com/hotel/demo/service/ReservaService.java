package com.hotel.demo.service;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	public Reserva guardarReserva(Reserva reserva) {
	    // Solo guarda la reserva sin detalles
	    return data.save(reserva);
	}

	@Transactional
	public Reserva actualizarReservaConDetalle(Reserva reserva) {
		// Guardar la reserva
	    reserva = guardarReserva(reserva);

	    // Obtener todos los detalles de servicio existentes para la reserva
	    List<Detalle_Servicio> detallesExistentes = (List<Detalle_Servicio>) listarNro(reserva.getNro_reserva());

	    // Convertir la lista de detalles existentes a un conjunto de IDs de servicios para fácil verificación
	    Set<Integer> serviciosExistentesIds = detallesExistentes.stream()
	        .map(detalle -> detalle.getServicio().getId_servicio())
	        .collect(Collectors.toSet());

	    // Iterar sobre los servicios de la reserva
	    for (Servicio servicio : reserva.getServicios()) {
	        Servicio servicioExistente = serviceS.listarId(servicio.getId_servicio());

	        if (servicioExistente != null) {
	            // Verificar si el servicio ya está en los detalles existentes
	            if (!serviciosExistentesIds.contains(servicio.getId_servicio())) {
	                // Si el servicio no existe, procesar el detalle del servicio
	                for (Empleado empleado : servicio.getEmpleados()) {
	                    Empleado empleadoExistente = serviceE.listarId(empleado.getId_emp());

	                    if (empleadoExistente != null) {
	                        // Crear y configurar el objeto Detalle_Servicio
	                        Detalle_Servicio detalleServicio = new Detalle_Servicio();
	                        detalleServicio.setReserva(reserva);  // Asociar la reserva guardada
	                        detalleServicio.setServicio(servicioExistente); // Asociar el servicio existente
	                        detalleServicio.setEmpleado(empleadoExistente); // Asociar el empleado existente
	                        detalleServicio.setHora_serv(new Time(Calendar.getInstance().getTimeInMillis()));
	                        detalleServicio.setEstado_serv("No realizado"); // Estado por defecto

	                        // Guardar el detalle del servicio
	                        serviceDS.Guardar(detalleServicio);
	                    } else {
	                        throw new RuntimeException("El empleado asociado al servicio no existe");
	                    }
	                }
	            } else {
	                // El servicio ya está asociado a la reserva
	                System.out.println("El servicio ya está asociado a esta reserva y no se puede duplicar: " + servicio.getId_servicio());
	            }
	        } else {
	            throw new RuntimeException("El servicio asociado a la reserva no existe");
	        }
	    }

	    return reserva;
	}
	
	@Transactional
	public Reserva crearReservaConDetalle(Reserva reserva) {
	    // Guardar la reserva
	    reserva = guardarReserva(reserva);

	    // Iterar sobre los servicios de la reserva
	    for (Servicio servicio : reserva.getServicios()) {
	        Servicio servicioExistente = serviceS.listarId(servicio.getId_servicio());

	        if (servicioExistente != null) {
	            // Iterar sobre los empleados asociados a este servicio
	            for (Empleado empleado : servicio.getEmpleados()) {
	                Empleado empleadoExistente = serviceE.listarId(empleado.getId_emp());

	                if (empleadoExistente != null) {
	                    // Crear y configurar el objeto Detalle_Servicio
	                    Detalle_Servicio detalleServicio = new Detalle_Servicio();
	                    detalleServicio.setReserva(reserva);  // Asociar la reserva guardada
	                    detalleServicio.setServicio(servicioExistente); // Asociar el servicio existente
	                    detalleServicio.setEmpleado(empleadoExistente); // Asociar el empleado existente
	                    detalleServicio.setHora_serv(new Time(Calendar.getInstance().getTimeInMillis()));
	                    detalleServicio.setEstado_serv("No realizado"); // Estado por defecto

	                    // Guardar el detalle del servicio
	                    serviceDS.Guardar(detalleServicio);
	                } else {
	                    throw new RuntimeException("El empleado asociado al servicio no existe");
	                }
	            }
	        } else {
	            throw new RuntimeException("El servicio asociado a la reserva no existe");
	        }
	    }

	    return reserva;
	}
	 

		    // Otros métodos

		    public double obtenerPrecioServicioPorId(int idServicio) {
		        Optional<Servicio> servicioOptional = serviceS.listarId1(idServicio);
		        return servicioOptional.map(Servicio::getPrecio).orElse(0.0); // Cambiado a 0.0 para que sea un valor double
		    }

		    // Otros métodos
		
	
}
