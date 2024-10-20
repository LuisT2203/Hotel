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
@Table(name = "tb_empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_emp;
	private String nombre_emp;
	private String apellido_emp;
	private String sexo_emp;
	private String cargo_emp;
	private String correo;    // Campo para el correo
    private String clave;     // Campo para la contraseña
	
	
}
