
  package com.hotel.demo.modelo; import java.sql.Time;
  
  import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
  import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
  import jakarta.persistence.Table;
  
  @Entity
  
  @Table(name = "detalle_servicio")
  public class Detalle_Servicio {
  
  @Id
  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_detaserv;
  private Time hora_serv; 
  private String estado_serv; 
  @ManyToOne 
  @JoinColumn(name="nro_reserva",referencedColumnName="nro_reserva")
  private Reserva reserva;
  
  
  @ManyToOne
  @JoinColumn(name="id_emp", referencedColumnName="id_emp") 
  private Empleado empleado;
  
  @ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio")
	private Servicio servicio;

  
  public Detalle_Servicio() { super(); }
  
  
  
  



public Detalle_Servicio(int id_detaserv, Time hora_serv, String estado_serv, Reserva reserva, Empleado empleado,
		Servicio servicio) {
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


public Reserva getReserva() {
	return reserva;
}



public void setReserva(Reserva reserva) {
	this.reserva = reserva;
}



public Empleado getEmpleado() {
	return empleado;
}


public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
}



public Servicio getServicio() {
	return servicio;
}



public void setServicio(Servicio servicio) {
	this.servicio = servicio;
}
  

  
  }
 
