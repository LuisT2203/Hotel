package com.hotel.demo.DTOS;

import java.time.LocalDate;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

	private int nro_reserva;

	@NotNull(message = "Campo Fecha Reserva no puede ser nulo")
	private LocalDate fecha_reserva;

	@NotNull(message = "Campo Cantidad Personas no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_personas;

	@NotNull(message = "Campo Cantidad Días no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_dias;

	@NotNull(message = "Campo Habitación no puede ser nulo")
	private HabitacionDTO habitacion;

	@NotNull(message = "Campo Huésped no puede ser nulo")
	private HuespedDTO huesped;

	@NotNull(message = "Campo Empleado no puede ser nulo")
	private EmpleadoDTO empleado;

	@NotNull(message = "Campo Servicio no puede ser nulo")
	private ServicioDTO servicio;

	@NotNull(message = "Campo Estado Reserva no puede ser nulo")
	private String estado_reserva;
}
