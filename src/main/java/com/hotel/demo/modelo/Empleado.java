package com.hotel.demo.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
@Entity
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
	
	public Empleado() {
		super();
	}

	

	public Empleado(Integer id_emp, String nombre_emp, String apellido_emp, String sexo_emp, String cargo_emp,
			String correo, String clave) {
		super();
		this.id_emp = id_emp;
		this.nombre_emp = nombre_emp;
		this.apellido_emp = apellido_emp;
		this.sexo_emp = sexo_emp;
		this.cargo_emp = cargo_emp;
		this.correo = correo;
		this.clave = clave;
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



	public Integer getId_emp() {
		return id_emp;
	}

	public void setId_emp(Integer id_emp) {
		this.id_emp = id_emp;
	}

	public String getNombre_emp() {
		return nombre_emp;
	}

	public void setNombre_emp(String nombre_emp) {
		this.nombre_emp = nombre_emp;
	}

	public String getApellido_emp() {
		return apellido_emp;
	}

	public void setApellido_emp(String apellido_emp) {
		this.apellido_emp = apellido_emp;
	}

	public String getSexo_emp() {
		return sexo_emp;
	}

	public void setSexo_emp(String sexo_emp) {
		this.sexo_emp = sexo_emp;
	}

	public String getCargo_emp() {
		return cargo_emp;
	}

	public void setCargo_emp(String cargo) {
		cargo_emp = cargo;
	}
	
	
}
