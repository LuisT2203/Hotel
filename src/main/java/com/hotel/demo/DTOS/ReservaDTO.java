package com.hotel.demo.DTOS;

import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {
	
	private int nro_reserva;
	@NotNull(message="Campo Fecha Reserva no puede ser nulo")
	private LocalDate fecha_reserva;
	@NotNull(message="Campo Cantidad Personas no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_personas;
	@NotNull(message="Campo Cantidad Dias no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_dias;
	@NotNull(message="Campo Habitacion no puede ser nulo")
	private HabitacionDTO habitacion;
	@NotNull(message="Campo Huesped no puede ser nulo")
	private HuespedDTO huesped;
	@NotNull(message="Campo Empleado no puede ser nulo")
	private EmpleadoDTO empleado;
	@NotNull(message="Campo Servicio no puede ser nulo")
	private ServicioDTO servicio;
	@NotNull(message="Campo Estado Reserva no puede ser nulo")
	private String estado_reserva;
	
	

	public ReservaDTO() {
		super();
	}

	public ReservaDTO(int nro_reserva, LocalDate fecha_reserva, int cant_personas, int cant_dias, HabitacionDTO habitacion,
			HuespedDTO huesped, EmpleadoDTO empleado, ServicioDTO servicio, String estado_reserva) {
		super();
		this.nro_reserva = nro_reserva;
		this.fecha_reserva = fecha_reserva;
		this.cant_personas = cant_personas;
		this.cant_dias = cant_dias;
		this.habitacion = habitacion;
		this.huesped = huesped;
		this.empleado = empleado;
		this.servicio = servicio;
		this.estado_reserva = estado_reserva;
	}

	public int getNro_reserva() {
		return nro_reserva;
	}

	public void setNro_reserva(int nro_reserva) {
		this.nro_reserva = nro_reserva;
	}

	public LocalDate getFecha_reserva() {
		return fecha_reserva;
	}

	public void setFecha_reserva(LocalDate fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}

	public int getCant_personas() {
		return cant_personas;
	}

	public void setCant_personas(int cant_personas) {
		this.cant_personas = cant_personas;
	}

	public int getCant_dias() {
		return cant_dias;
	}

	public void setCant_dias(int cant_dias) {
		this.cant_dias = cant_dias;
	}

	
	public String getEstado_reserva() {
		return estado_reserva;
	}



	public void setEstado_reserva(String estado_reserva) {
		this.estado_reserva = estado_reserva;
	}

	public HabitacionDTO getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(HabitacionDTO habitacion) {
		this.habitacion = habitacion;
	}

	public HuespedDTO getHuesped() {
		return huesped;
	}

	public void setHuesped(HuespedDTO huesped) {
		this.huesped = huesped;
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public ServicioDTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioDTO servicio) {
		this.servicio = servicio;
	}



	
	
}
