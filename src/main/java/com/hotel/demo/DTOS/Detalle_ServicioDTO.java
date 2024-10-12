
  package com.hotel.demo.DTOS; import java.sql.Time;
  
import jakarta.validation.constraints.NotNull;
  
 
  public class Detalle_ServicioDTO {
 
  private int id_detaserv;
  private Time hora_serv; 
  @NotNull(message="Campo Estado Servicio no puede ser nulo")
  private String estado_serv; 
  @NotNull(message="Campo Reserva no puede ser nulo")
  private ReservaDTO reserva;
  @NotNull(message="Campo Empleado no puede ser nulo")
  private EmpleadoDTO empleado;
  @NotNull(message="Campo Servicio no puede ser nulo")
  private ServicioDTO servicio;

  
  public Detalle_ServicioDTO() { super(); }
  
  
  
  



public Detalle_ServicioDTO(int id_detaserv, Time hora_serv, String estado_serv, ReservaDTO reserva, EmpleadoDTO empleado,
		ServicioDTO servicio) {
	super();
	this.id_detaserv = id_detaserv;
	this.hora_serv = hora_serv;
	this.estado_serv = estado_serv;
	this.reserva = reserva;
	this.empleado = empleado;
	this.servicio = servicio;
}


public int getId_detaserv() { return id_detaserv; }
  
  public void setId_detaserv(int id_detaserv) { this.id_detaserv = id_detaserv;
  }
  
  public Time getHora_serv() { return hora_serv; }
  
  public void setHora_serv(Time hora_serv) { this.hora_serv = hora_serv; }
  
  public String getEstado_serv() { return estado_serv; }
  
  public void setEstado_serv(String estado_serv) { this.estado_serv =
  estado_serv; }


public ReservaDTO getReserva() {
	return reserva;
}



public void setReserva(ReservaDTO reserva) {
	this.reserva = reserva;
}



public EmpleadoDTO getEmpleado() {
	return empleado;
}


public void setEmpleado(EmpleadoDTO empleado) {
	this.empleado = empleado;
}



public ServicioDTO getServicio() {
	return servicio;
}



public void setServicio(ServicioDTO servicio) {
	this.servicio = servicio;
}
  

  
  }
 
