package com.hotel.demo.DTOS;

import jakarta.validation.constraints.NotNull;

public class EmpleadoDTO {
	
	private Integer id_emp;
	@NotNull(message="Campo nombre Empleado no puede ser nulo")
	private String nombre_emp;
	@NotNull(message="Campo Apellido Empleado no puede ser nulo")
	private String apellido_emp;
	@NotNull(message="Campo Sexo Empleado no puede ser nulo")
	private String sexo_emp;
	@NotNull(message="Campo Cargo Empleado no puede ser nulo")
	private String cargo_emp;
	@NotNull(message="Campo Correo Empleado no puede ser nulo")
	private String correo;
	@NotNull(message="Campo Clave Empleado no puede ser nulo")
	private String clave;
	
	public EmpleadoDTO() {
		super();
	}







	public EmpleadoDTO(Integer id_emp, @NotNull(message = "Campo nombre Empleado no puede ser nulo") String nombre_emp,
			@NotNull(message = "Campo Apellido Empleado no puede ser nulo") String apellido_emp,
			@NotNull(message = "Campo Sexo Empleado no puede ser nulo") String sexo_emp,
			@NotNull(message = "Campo Cargo Empleado no puede ser nulo") String cargo_emp,
			@NotNull(message = "Campo Correo Empleado no puede ser nulo") String correo,
			@NotNull(message = "Campo Clave Empleado no puede ser nulo") String clave) {
		super();
		this.id_emp = id_emp;
		this.nombre_emp = nombre_emp;
		this.apellido_emp = apellido_emp;
		this.sexo_emp = sexo_emp;
		this.cargo_emp = cargo_emp;
		this.correo = correo;
		this.clave = clave;
	}







	public Integer getId_emp() {
		return id_emp;
	}







	public void setId_emp(Integer id_emp) {
		this.id_emp = id_emp;
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
