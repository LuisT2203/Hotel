package com.hotel.demo.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.demo.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findOneByUsuarioAndClave(String usuario,String clave);
	
	public Usuario findByUsuario (String usuario); 
	
	
	
	
}
