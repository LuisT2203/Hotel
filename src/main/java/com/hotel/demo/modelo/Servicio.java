package com.hotel.demo.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Table (name="tb_servicio")

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor 
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_servicio;
	private String tipo;
	private String nombre;
	private double precio;
	
	// Relación con Empleado
	@ManyToOne
	@JoinColumn(name="id_emp", referencedColumnName="id_emp")
    private Empleado empleado;

   
	

	

	
	
}
