package com.hotel.demo.modelo;

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
@Table(name = "registro_pago")
public class Registro_Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_pago;
	private double precio;
	private int cant_dias;
	private int precio_habi;
	private double monto;
	@ManyToOne
	  @JoinColumn(name="nro_reserva", referencedColumnName="nro_reserva")
	  private Reserva reserva;

	
	
	
}
