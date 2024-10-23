package com.hotel.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IRegistroPago;



import com.hotel.demo.modelo.Detalle_Reserva;
import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Registro_Pago;
import com.hotel.demo.modelo.Reserva;
import com.hotel.demo.modelo.Servicio;
@Service
public class RegistroPagoService {
	@Autowired
	private IRegistroPago data;
	@Autowired
    private ReservaService serviceR;
	
	
	public List<Registro_Pago> listarRegPago() {
		return (List<Registro_Pago>)data.findAll();
	}

	
	public Registro_Pago listarId(int Id_pago) {
		// TODO Auto-generated method stub
		return data.findById(Id_pago).orElse(new Registro_Pago());
	}

	public Registro_Pago Guardar(Registro_Pago rp) {
		return  data.save(rp);    
	}
	
	
	public Registro_Pago Borrar(int Id_pago) {
		Registro_Pago temp = data.findById(Id_pago).orElse(null);
		if(temp==null) {
			return new Registro_Pago();
		}else {
			data.deleteById(Id_pago);
			return temp;
		
		}
	}

	
	public Registro_Pago GuardarRegistro(Registro_Pago registroPago) {
		// Obtener la reserva por número de reserva
	    Reserva reserva = serviceR.listarNro(registroPago.getReserva().getNro_reserva());

	    if (reserva != null) {
	        // Guardar el registro de pago
	        registroPago = Guardar(registroPago);

	        // Configurar el objeto Registro_Pago
	        registroPago.setReserva(reserva);
	        // Configurar otros campos según sea necesario
	        
	        // Guardar el registro de pago
	        Guardar(registroPago);

	        // Actualizar el estado de la reserva
	        reserva.setEstado_reserva("Pagado"); // Establecer el estado como "Pagado"
	        serviceR.guardarReserva(reserva);// Guardar la reserva actualizada

	        return registroPago;
	    } else {
	        throw new RuntimeException("La Reserva asociada al registro de pago no existe");
	    }
	}

	
	





}
