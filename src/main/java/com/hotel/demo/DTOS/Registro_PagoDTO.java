package com.hotel.demo.DTOS;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;


public class Registro_PagoDTO {
	
	private int id_pago;
	@NotNull(message="Campo Precio Servicio no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private double precio;
	@NotNull(message="Campo Cantidad de dias no puede ser nulo")
	@Digits(integer = 10, fraction = 0, message = "El campo debe contener solo números enteros")
	private int cant_dias;
	@NotNull(message="Campo Precio Habitacion no puede ser nulo")
	@Digits(integer = 10, fraction = 2, message = "El campo debe contener solo números enteros")
	private int precio_habi;
	@NotNull(message="Campo Monto no puede ser nulo")
	@Digits(integer = 10, fraction = 2, message = "El campo debe contener solo números enteros")
	private double monto;
	@NotNull(message="Campo Reserva no puede ser nulo")
	private ReservaDTO reserva;

	
	public Registro_PagoDTO() {

	}
	
	
	
	
	
	public ReservaDTO getReserva() {
		return reserva;
	}





	public void setReserva(ReservaDTO reserva) {
		this.reserva = reserva;
	}





	public Registro_PagoDTO(int id_pago, double precio, int cant_dias, int precio_habi, double monto, ReservaDTO reserva) {
		super();
		this.id_pago = id_pago;
		this.precio = precio;
		this.cant_dias = cant_dias;
		this.precio_habi = precio_habi;
		this.monto = monto;
		this.reserva = reserva;
	}





	public int getId_pago() {
		return id_pago;
	}
	public void setId_pago(int id_pago) {
		this.id_pago = id_pago;
	}
	
	
	
	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public int getPrecio_habi() {
		return precio_habi;
	}
	public void setPrecio_habi(int precio_habi) {
		this.precio_habi = precio_habi;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	public int getCant_dias() {
		return cant_dias;
	}

	public void setCant_dias(int cant_dias) {
		this.cant_dias = cant_dias;
	}



	
	

	
}
