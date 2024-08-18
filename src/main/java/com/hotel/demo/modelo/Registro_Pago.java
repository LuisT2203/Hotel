package com.hotel.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_pago")
public class Registro_Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_pago;
	private double precio;
	private int cant_dias;
	private int precio_habi;
	private double monto;
	@ManyToOne
	  @JoinColumn(name="nro_reserva", referencedColumnName="nro_reserva")
	  private Reserva reserva;

	
	public Registro_Pago() {

	}
	
	
	
	
	
	public Reserva getReserva() {
		return reserva;
	}





	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}





	public Registro_Pago(int id_pago, double precio, int cant_dias, int precio_habi, double monto, Reserva reserva) {
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
