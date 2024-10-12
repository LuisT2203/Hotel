package com.hotel.demo.DTOS;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;


public class HuespedDTO {
	
	private int id_huesped;
	@NotNull(message="Campo Nombre Huesped no puede ser nulo")
	private String nombre;
	@NotNull(message="Campo Apellido Huesped no puede ser nulo")
	private String apellido;
	@NotNull(message="Campo Direccion Huesped no puede ser nulo")
	private String direccion;
	@NotNull(message="Campo Email Huesped no puede ser nulo")
	private String email;	
	@NotNull(message="Campo Sexo Huesped no puede ser nulo")
	private String sexo;
	@NotNull(message="Campo Edad Huesped no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int edad;
	
	public HuespedDTO() {
		super();
	}

	public HuespedDTO(int id_huesped, String nombre, String apellido, String direccion, String email, String sexo, int edad) {
		super();
		this.id_huesped = id_huesped;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.email = email;
		this.sexo = sexo;
		this.edad = edad;
	}

	public int getId_huesped() {
		return id_huesped;
	}

	public void setId_huesped(int id_huesped) {
		this.id_huesped = id_huesped;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
}

