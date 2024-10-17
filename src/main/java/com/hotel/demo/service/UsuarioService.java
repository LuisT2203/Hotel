package com.hotel.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.demo.modelo.Usuario;
import com.hotel.demo.DTOS.LoginDTO;
import com.hotel.demo.DTOS.UsuarioDTO;
import com.hotel.demo.interfacesService.IUsuarioService;
import com.hotel.demo.response.LoginResponse;
import com.hotel.demo.interfaces.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired 
	private UsuarioRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	/*
	 * @Override public String addUsuario(UsuarioDTO usuarioDTO) {
	 * 
	 * Usuario usuario = new Usuario(
	 * 
	 * 
	 * usuarioDTO.getCorreo() usuarioDTO.getTipo(),
	 * 
	 * this.passwordEncoder.encode(usuarioDTO.getClave())
	 * 
	 * ); repository.save(usuario);
	 * 
	 * return usuario.getUsuario(); }
	 */
	
		
		  
		  
	public Usuario usuarioIns(Usuario usuario) { 
		
		return repository.save(usuario);
	}




	@Override
	public LoginResponse loginUsuario(LoginDTO loginDTO) {
		String msg = "";
		Usuario usuario1 = repository.findByUsuario(loginDTO.getCorreo());
		if(usuario1 != null) {
			String password = loginDTO.getClave();
			String encodedPassword = usuario1.getClave();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if(isPwdRight) {
				Optional<Usuario> usuario = repository.findOneByUsuarioAndClave(loginDTO.getCorreo(),encodedPassword);
				if(usuario.isPresent()) {
					return new LoginResponse("Login Succes",true);
				}else {
					return new LoginResponse("Login Failed",false);
				}			
				
			}else {
				return new LoginResponse("Password Not Match",false);
			}
		}else {
			return new LoginResponse("Email not exists",false);
		}
	}




	@Override
	public String addUsuario(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}
		 
}
