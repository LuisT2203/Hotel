package com.hotel.demo.DTOS;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registro_PagoDTO {

	private int id_pago;

	@NotNull(message = "Campo Precio Servicio no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private double precio;

	@NotNull(message = "Campo Cantidad de días no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_dias;

	@NotNull(message = "Campo Precio Habitación no puede ser nulo")
	@Digits(integer = 10, fraction = 2, message = "El campo debe contener un valor numérico válido con dos decimales")
	private double precio_habi;

	@NotNull(message = "Campo Monto no puede ser nulo")
	@Digits(integer = 10, fraction = 2, message = "El campo debe contener un valor numérico válido con dos decimales")
	private double monto;

	@NotNull(message = "Campo Reserva no puede ser nulo")
	private ReservaDTO reserva;
}
