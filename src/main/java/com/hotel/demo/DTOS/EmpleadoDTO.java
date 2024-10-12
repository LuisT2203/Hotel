package com.hotel.demo.DTOS;

import jakarta.validation.constraints.NotNull;

public class EmpleadoDTO {
	
	private Integer id_emp;
	@NotNull(message="Campo Nombre Empleado no puede ser nulo")
	private String nombre_emp;
	@NotNull(message="Campo Apellido Empleado no puede ser nulo")
	private String apellido_emp;
	@NotNull(message="Campo Sexo Empleado no puede ser nulo")
	private String sexo_emp;
	@NotNull(message="Campo Cargo Empleado no puede ser nulo")
	private String cargo_emp;
	
	public EmpleadoDTO() {
		super();
	}

	public EmpleadoDTO(Integer id_emp, String nombre_emp, String apellido_emp, String sexo_emp, String cargo_emp) {
		super();
		this.id_emp = id_emp;
		this.nombre_emp = nombre_emp;
		this.apellido_emp = apellido_emp;
		this.sexo_emp = sexo_emp;
		this.cargo_emp = cargo_emp;
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
