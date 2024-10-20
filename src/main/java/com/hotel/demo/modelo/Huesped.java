package com.hotel.demo.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor 
@Table(name = "tb_huesped")
public class Huesped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_huesped;
	private String nombre;
	private String apellido;
	private String direccion;
	private String correo;	
	private String sexo;
	private int edad;
	private String clave;
	
	
}

