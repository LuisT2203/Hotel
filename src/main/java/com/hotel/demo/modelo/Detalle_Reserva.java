package com.hotel.demo.modelo;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
@Entity
@Table(name = "detalle_reserva")
public class Detalle_Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_detareser;
	private LocalDate fecha_ingreso;
	private String hora_entrada;
	private String hora_salida;
	private LocalDate fecha_salida;
	
	@ManyToOne
	@JoinColumn(name="nro_habi", referencedColumnName="nro_habi")
	private Habitacion habitacion;
	@ManyToOne 
	  @JoinColumn(name="nro_reserva", referencedColumnName="nro_reserva")
	  private Reserva reserva;
	
	public Detalle_Reserva() {
		super();
	}
	


	public Detalle_Reserva(int id_detareser, LocalDate fecha_ingreso, String hora_entrada, String hora_salida,
			LocalDate fecha_salida, Habitacion habitacion, Reserva reserva) {
		super();
		this.id_detareser = id_detareser;
		this.fecha_ingreso = fecha_ingreso;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		this.fecha_salida = fecha_salida;
		this.habitacion = habitacion;
		this.reserva = reserva;
	}



	public int getId_detareser() {
		return id_detareser;
	}



	public void setId_detareser(int id_detareser) {
		this.id_detareser = id_detareser;
	}



	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(String hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public String getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(String hora_salida) {
		this.hora_salida = hora_salida;
	}

	public LocalDate getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}



	public Habitacion getHabitacion() {
		return habitacion;
	}



	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}



	public Reserva getReserva() {
		return reserva;
	}



	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	

	
}
