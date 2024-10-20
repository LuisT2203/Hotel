package com.hotel.demo.modelo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor 
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
	
	@ManyToMany
	@JoinTable(
	    name = "reserva_servicios", 
	    joinColumns = @JoinColumn(name = "nro_reserva"),
	    inverseJoinColumns = @JoinColumn(name = "id_servicio")
	)
	private List<Servicio> servicios = new ArrayList<>();

    

	private String estado_reserva;
	
	

	
	
	
}
