package com.hotel.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IEmpleado;
import com.hotel.demo.interfaces.IHuesped;
import com.hotel.demo.interfaces.UsuarioRepository;
import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.modelo.Usuario;
@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	 @Autowired
	    private IEmpleado empleadoRepository;

	    @Autowired
	    private IHuesped huespedRepository;

	    @Override
	    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
	        // Buscar primero en la tabla de Empleados
	        Empleado empleado = empleadoRepository.findByCorreo(correo);
	        if (empleado != null) {
	            // Si es empleado, devolver UserDetails con los datos del empleado
	            return new User(empleado.getCorreo(), empleado.getClave(), new ArrayList<>());
	        }

	        // Si no es empleado, buscar en la tabla de Huespedes
	        Huesped huesped = huespedRepository.findByCorreo(correo);
	        if (huesped != null) {
	            // Si es huesped, devolver UserDetails con los datos del huesped
	            return new User(huesped.getCorreo(), huesped.getClave(), new ArrayList<>());
	        }

	        // Si no lo encuentra ni en Empleados ni en Huespedes, lanzar excepción
	        throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo);
	    }

}
