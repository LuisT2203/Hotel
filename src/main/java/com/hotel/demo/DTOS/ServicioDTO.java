package com.hotel.demo.DTOS;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {

	private int id_servicio;

	@NotNull(message = "Campo Tipo Servicio no puede ser nulo")
	private String tipo;

	@NotNull(message = "Campo Nombre Servicio no puede ser nulo")
	private String nombre;

	@NotNull(message = "Campo Precio Servicio no puede ser nulo")
	@Digits(integer = 10, fraction = 2, message = "El campo debe contener solo números con hasta 2 decimales")
	private double precio;
}
