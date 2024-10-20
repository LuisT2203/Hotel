package com.hotel.demo.modelo;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table (name="tb_servicio")
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

   
	

	

	public Servicio(int id_servicio, String tipo, String nombre, double precio, Empleado empleado) {
		super();
		this.id_servicio = id_servicio;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.empleado = empleado;
	}

	public Servicio() {
		super();
	}
	

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
