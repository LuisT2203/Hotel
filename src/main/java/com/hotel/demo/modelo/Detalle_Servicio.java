
  package com.hotel.demo.modelo; import java.sql.Time;
  
  import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
  import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
  import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
  
  @Entity
  @Data // Genera getters, setters, toString, equals y hashCode
  @NoArgsConstructor // Genera un constructor sin parámetros
  @AllArgsConstructor 
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

  
 

  
  }
 
