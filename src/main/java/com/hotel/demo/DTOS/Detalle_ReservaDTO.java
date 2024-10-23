package com.hotel.demo.DTOS;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Detalle_ReservaDTO {
	
	
	private int id_detareser;
	@NotNull(message="Campo Fecha de Ingreso no puede ser nulo")
	private LocalDate fecha_ingreso;
	@NotBlank(message="Campo Hora de Entrada de Ingreso no puede ser nulo")
	private String hora_entrada;
	@NotBlank(message="Campo Hora de Salida no puede ser nulo")
	private String hora_salida;
	@NotNull(message="Campo Fecha de Salida no puede ser nulo")
	private LocalDate fecha_salida;
	@NotNull(message="Campo Habitacion de Salida no puede ser nulo")
	private HabitacionDTO habitacion;
	@NotNull(message="Campo Reserva de Salida no puede ser nulo")
	private ReservaDTO reserva;
	
	public Detalle_ReservaDTO() {
		super();
	}
	


	public Detalle_ReservaDTO(int id_detareser, LocalDate fecha_ingreso, String hora_entrada, String hora_salida,
			LocalDate fecha_salida, HabitacionDTO habitacion, ReservaDTO reserva) {
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



	public HabitacionDTO getHabitacion() {
		return habitacion;
	}



	public void setHabitacion(HabitacionDTO habitacion) {
		this.habitacion = habitacion;
	}



	public ReservaDTO getReserva() {
		return reserva;
	}



	public void setReserva(ReservaDTO reserva) {
		this.reserva = reserva;
	}

	

	
}
