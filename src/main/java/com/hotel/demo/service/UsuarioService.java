package com.hotel.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.modelo.Usuario;
import com.hotel.demo.interfaces.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario usuarioIns(Usuario usuario) {
		return repository.save(usuario);
	}
}
