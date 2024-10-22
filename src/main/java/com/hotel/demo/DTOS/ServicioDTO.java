package com.hotel.demo.DTOS;

import com.hotel.demo.modelo.Empleado;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class ServicioDTO {
	
	private int id_servicio;
	@NotNull(message="Campo Tipo Servicio no puede ser nulo")
	private String tipo;
	@NotNull(message="Campo Nombre Servicio no puede ser nulo")
	private String nombre;
	@NotNull(message="Campo Precio Servicio no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private double precio;
	@NotNull(message="Campo Empleado Servicio no puede ser nulo")
	private EmpleadoDTO empleado;
	
	

	public ServicioDTO(int id_servicio, @NotNull(message = "Campo Tipo Servicio no puede ser nulo") String tipo,
			@NotNull(message = "Campo Nombre Servicio no puede ser nulo") String nombre,
			@NotNull(message = "Campo Precio Servicio no puede ser nulo") @Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros") double precio,
			@NotNull(message = "Campo Empleado Servicio no puede ser nulo") EmpleadoDTO empleado) {
		super();
		this.id_servicio = id_servicio;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.empleado = empleado;
	}

	public ServicioDTO() {
		super();
	}

	
	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
