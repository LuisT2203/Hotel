package com.hotel.demo.modelo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "tb_reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nro_reserva;
	private LocalDate fecha_reserva;
	private int cant_personas;
	private int cant_dias;
	
	@ManyToOne
	@JoinColumn(name="nro_habi", referencedColumnName="nro_habi")
	private Habitacion habitacion;
	
	@ManyToOne
	@JoinColumn(name="id_huesped", referencedColumnName="id_huesped")
	private Huesped huesped;
	
	@ManyToOne
	@JoinColumn(name = "id_emp", referencedColumnName = "id_emp")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="id_servicio",  referencedColumnName = "id_servicio")
	private Servicio servicio;

	private String estado_reserva;
	
	

	public Reserva() {
		super();
	}

	public Reserva(int nro_reserva, LocalDate fecha_reserva, int cant_personas, int cant_dias, Habitacion habitacion,
			Huesped huesped, Empleado empleado, Servicio servicio, String estado_reserva) {
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

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}



	
	
}
