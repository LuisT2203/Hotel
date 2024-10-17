package com.hotel.demo.interfacesService;

import com.hotel.demo.DTOS.LoginDTO;
import com.hotel.demo.DTOS.UsuarioDTO;
import com.hotel.demo.response.LoginResponse;


public interface IUsuarioService {

	String addUsuario(UsuarioDTO usuarioDTO);

	LoginResponse loginUsuario(LoginDTO loginDTO);

	

}
