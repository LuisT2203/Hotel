package com.hotel.demo.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_reserva")
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
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
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio")
	private Servicio servicio;

	private String estado_reserva;
}
