package com.hotel.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IEmpleado;

import com.hotel.demo.modelo.Empleado;


@Service
public class EmpleadoService{
	@Autowired
	private IEmpleado data;
	@Autowired
	private PasswordEncoder passwordEncoder;  // Inyección del encoder
	
	public List<Empleado> listarEmpleado() {
		return (List<Empleado>)data.findAll();
	}

	
	public Empleado listarId(Integer Id_emp) {
		// TODO Auto-generated method stub
		return data.findById(Id_emp).orElse(new Empleado());
	}

	
	public Empleado Guardar(Empleado e) {
        // Encriptamos la contraseña antes de guardar
        e.setClave(passwordEncoder.encode(e.getClave()));
        return data.save(e);
    }

	
	public Empleado Borrar(Integer Id_emp) {
		Empleado temp = data.findById(Id_emp).orElse(null);
		if(temp==null) {
			return new Empleado();
		}else {
			data.deleteById(Id_emp);
			return temp;
		}
	}

}
