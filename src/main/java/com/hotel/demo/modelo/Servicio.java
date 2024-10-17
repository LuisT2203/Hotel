package com.hotel.demo.modelo;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany // o @OneToMany, dependiendo de tu diseño
    @JoinTable(
        name = "servicio_empleado",
        joinColumns = @JoinColumn(name = "id_servicio"),
        inverseJoinColumns = @JoinColumn(name = "id_emp")
    )
    private List<Empleado> empleados;

    // Getters y setters
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
	
	

	public Servicio(int id_servicio, String tipo, String nombre, double precio, List<Empleado> empleados) {
		super();
		this.id_servicio = id_servicio;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.empleados = empleados;
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
