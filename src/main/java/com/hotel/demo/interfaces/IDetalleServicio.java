package com.hotel.demo.interfaces;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.modelo.Detalle_Servicio;
@Repository
public interface IDetalleServicio extends CrudRepository<Detalle_Servicio, Integer> {
	
}
