package com.hotel.demo.DTOS;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class HabitacionDTO {
	
	private int nro_habi;
	@NotNull(message="Campo Descripcion Habitacion no puede ser nulo")
	private String descripcion;
	@NotNull(message="Campo Estado Habitacion no puede ser nulo")
	private String estado;
	@NotNull(message="Campo Precio Habitacion no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int precio_habi;
	
	public HabitacionDTO() {
		super();
	}

	

	public HabitacionDTO(int nro_habi, String descripcion, String estado, int precio_habi) {
		super();
		this.nro_habi = nro_habi;
		this.descripcion = descripcion;
		this.estado = estado;
		this.precio_habi = precio_habi;
	}



	public int getNro_habi() {
		return nro_habi;
	}

	public void setNro_habi(int nro_habi) {
		this.nro_habi = nro_habi;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public int getPrecio_habi() {
		return precio_habi;
	}



	public void setPrecio_habi(int precio_habi) {
		this.precio_habi = precio_habi;
	}
	@Transactional
	public void reservar() {
		setEstado("NoDisponible") ;
    }


	@Transactional
	public void disponibilizar() {
		setEstado("Disponible") ;
	}
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="nro_reserva", insertable=false,updatable=false) private
	 * Reserva objReserva;
	 * 
	 * public Reserva getObjReserva() { return objReserva; }
	 * 
	 * 
	 * 
	 * public void setObjReserva(Reserva objReserva) { this.objReserva = objReserva;
	 * }
	 */
	
	
}
