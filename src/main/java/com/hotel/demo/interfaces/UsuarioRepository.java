package com.hotel.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByCorreo (String correo);
}
