package com.hotel.demo.DTOS;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuespedDTO {

	private int id_huesped;

	@NotNull(message = "Campo Nombre Huesped no puede ser nulo")
	private String nombre;

	@NotNull(message = "Campo Apellido Huesped no puede ser nulo")
	private String apellido;

	@NotNull(message = "Campo Direccion Huesped no puede ser nulo")
	private String direccion;

	@NotNull(message = "Campo Email Huesped no puede ser nulo")
	private String email;

	@NotNull(message = "Campo Sexo Huesped no puede ser nulo")
	private String sexo;

	@NotNull(message = "Campo Edad Huesped no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int edad;
}
