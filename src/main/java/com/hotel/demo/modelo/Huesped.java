package com.hotel.demo.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_huesped")
@Data // Genera automáticamente getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los atributos
public class Huesped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_huesped;

	private String nombre;
	private String apellido;
	private String direccion;
	private String email;
	private String sexo;
	private int edad;
}
