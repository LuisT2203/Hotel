package com.hotel.demo.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor 
@Table(name = "tb_habitacion")
public class Habitacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nro_habi;
	private String descripcion;
	private String estado;
	private int precio_habi;
	
	
	@Transactional
	public void reservar() {
		setEstado("NoDisponible") ;
    }


	@Transactional
	public void disponibilizar() {
		setEstado("Disponible") ;
	}
	
	
	
	
}
