package com.hotel.demo.interfaces;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.modelo.Empleado;
@Repository
public interface IEmpleado extends CrudRepository<Empleado, Integer> {
	Empleado findByCorreo(String correo);
}
