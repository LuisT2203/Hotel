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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor 
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
	
	
	
}
