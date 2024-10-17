package com.hotel.demo.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
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
	
	public Huesped() {
		super();
	}

	
	

	public Huesped(int id_huesped, String nombre, String apellido, String direccion, String correo, String sexo,
			int edad, String clave) {
		super();
		this.id_huesped = id_huesped;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.sexo = sexo;
		this.edad = edad;
		this.clave = clave;
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

	

	public String getCorreo() {
		return correo;
	}




	public void setCorreo(String correo) {
		this.correo = correo;
	}




	public String getClave() {
		return clave;
	}




	public void setClave(String clave) {
		this.clave = clave;
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

