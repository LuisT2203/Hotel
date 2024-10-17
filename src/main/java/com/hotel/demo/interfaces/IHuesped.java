package com.hotel.demo.interfaces;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.modelo.Huesped;
@Repository
public interface IHuesped extends CrudRepository<Huesped, Integer>{
	Huesped findByCorreo(String correo);
}
