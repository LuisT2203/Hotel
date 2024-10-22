package com.hotel.demo.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="tb_servicio")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private int id_servicio;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "precio")
	private double precio;
	
	public Servicio(int id_servicio, String tipo, String nombre, double precio) {
		super();
		this.id_servicio = id_servicio;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Servicio() {
		super();
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
