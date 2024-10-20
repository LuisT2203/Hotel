package com.hotel.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.demo.interfaces.IRegistroPago;
import com.hotel.demo.modelo.Registro_Pago;
import com.hotel.demo.modelo.Reserva;

@Service
public class RegistroPagoService {

	@Autowired
	private IRegistroPago data;

	@Autowired
	private ReservaService serviceR;

	public List<Registro_Pago> listarRegPago() {
		return (List<Registro_Pago>) data.findAll();
	}

	public Registro_Pago listarId(int id_pago) {
		return data.findById(id_pago).orElse(null);
	}

	public Registro_Pago guardar(Registro_Pago registroPago) {
		return data.save(registroPago);
	}

	public Registro_Pago borrar(int id_pago) {
		Registro_Pago temp = data.findById(id_pago).orElse(null);
		if (temp == null) {
			return null; // Cambié para devolver null si no se encuentra
		} else {
			data.deleteById(id_pago);
			return temp;
		}
	}

	public Registro_Pago guardarRegistro(Registro_Pago registroPago) {
		// Obtener la reserva por número de reserva
		Reserva reserva = serviceR.listarNro(registroPago.getReserva().getNro_reserva());

		if (reserva != null) {
			// Guardar el registro de pago
			registroPago = guardar(registroPago); // Usar método guardar

			// Configurar el objeto Registro_Pago
			registroPago.setReserva(reserva);
			// Actualizar el estado de la reserva
			reserva.setEstado_reserva("Pagado"); // Establecer el estado como "Pagado"
			serviceR.GuardarR(reserva); // Guardar la reserva actualizada

			return registroPago;
		} else {
			throw new RuntimeException("La Reserva asociada al registro de pago no existe");
		}
	}
}
