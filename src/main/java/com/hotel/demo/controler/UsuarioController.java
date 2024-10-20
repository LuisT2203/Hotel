package com.hotel.demo.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hotel.demo.interfacesService.IUsuarioService;
import com.hotel.demo.modelo.Huesped;
import com.hotel.demo.modelo.Usuario;
import com.hotel.demo.response.LoginResponse;
import com.hotel.demo.service.HuespedService;
import com.hotel.demo.service.JwtUtilService;
import com.hotel.demo.service.UsuarioService;
import com.hotel.demo.modelo.Empleado;
import com.hotel.demo.service.EmpleadoService;
import com.hotel.demo.DTOS.AuthResponseDto;
import com.hotel.demo.DTOS.LoginDTO;
import com.hotel.demo.DTOS.UsuarioDTO;
import com.hotel.demo.interfaces.IEmpleado;
import com.hotel.demo.interfaces.IHuesped;
import com.hotel.demo.interfaces.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private IUsuarioService serviceU;
    
    @Autowired 
    private UsuarioRepository usuRepo;
    
    @Autowired
    private IEmpleado empRepo;
    
    @Autowired
    private IHuesped huesRepo;
    
    @Autowired
    private JwtUtilService jwtUtilService;
    
	/*
	 * @PostMapping(path="/save") public String saveUsuario(@RequestBody UsuarioDTO
	 * usuarioDTO) { String id = serviceU.addUsuario(usuarioDTO); return id;
	 * 
	 * }
	 */
    @Autowired
    private HuespedService huespedService;
    
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/save")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        if ("Huesped".equalsIgnoreCase(usuarioDTO.getTipo())) {
            // Crear Huésped
            Huesped huesped = new Huesped();
            huesped.setCorreo(usuarioDTO.getCorreo());
            huesped.setClave(usuarioDTO.getClave());
            huespedService.Guardar(huesped);
            return ResponseEntity.ok("Huesped registrado exitosamente");
        } else if ("Empleado".equalsIgnoreCase(usuarioDTO.getTipo())) {
            // Crear Empleado
            Empleado empleado = new Empleado();
            empleado.setCorreo(usuarioDTO.getCorreo());
            empleado.setClave(usuarioDTO.getClave());
            empleadoService.Guardar(empleado);
            return ResponseEntity.ok("Empleado registrado exitosamente");
        } else {
            return ResponseEntity.badRequest().body("Tipo de usuario inválido");
        }
    }


	/*
	 * @PostMapping(path="/login") public ResponseEntity<?>
	 * loginUsuario(@RequestBody LoginDTO loginDTO){
	 * 
	 * try { //Autenticamos el usuario con authenticationManager
	 * this.authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken( loginDTO.getUsuario(),
	 * loginDTO.getClave() )); //validar en la bd UserDetails userDetails =
	 * this.userDetailsService.loadUserByUsername(loginDTO.getUsuario()); Usuario
	 * usuario = usuRepo.findByUsuario(loginDTO.getUsuario()); //generar token
	 * String jwt = this.jwtUtilService.generateToken(userDetails,
	 * usuario.getTipo()); String refreshToken =
	 * this.jwtUtilService.generateRefreshToken(userDetails, usuario.getTipo());
	 * 
	 * AuthResponseDto authResponseDto = new AuthResponseDto();
	 * authResponseDto.setToken(jwt); authResponseDto.setRefreshToken(refreshToken);
	 * 
	 * return new ResponseEntity<AuthResponseDto>(authResponseDto, HttpStatus.OK);
	 * }catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Athentication::" +
	 * e.getMessage()); } }
	 */
    @PostMapping(path="/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginDTO loginDTO) {
        try {
            // Llamamos a authenticationManager para que Spring valide las credenciales
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDTO.getCorreo(), loginDTO.getClave()
                )
            );

            // Buscar en la tabla de Empleados
            Empleado empleado = empRepo.findByCorreo(loginDTO.getCorreo());

            // Si no es empleado, buscar en la tabla de Huespedes
            Huesped huesped = empleado == null ? huesRepo.findByCorreo(loginDTO.getCorreo()) : null;

            // Si no existe en ninguna tabla, el usuario no se encuentra
            if (empleado == null && huesped == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
            }

            // Determinar el tipo de usuario según la tabla en la que lo encontramos
            String tipoUsuario = empleado != null ? "Empleado" : "Huesped";

            // Generar token JWT con el tipo de usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getCorreo());
            String jwt = jwtUtilService.generateToken(userDetails, tipoUsuario);
            String refreshToken = jwtUtilService.generateRefreshToken(userDetails, tipoUsuario);

            // Enviar la respuesta con los tokens
            AuthResponseDto authResponseDto = new AuthResponseDto(jwt, refreshToken);
            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

        } catch (Exception e) {
            // Capturar el error de autenticación y mostrar mensaje
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación: " + e.getMessage());
        }
    }
		
	/*
	 * @PostMapping(path="/refresh") public ResponseEntity<?>
	 * loginUsuario(@RequestBody Map<String, String> request){ String refreshToken =
	 * request.get("refreshToken");
	 * 
	 * try {
	 * 
	 * String username = jwtUtilService.extractUsername(refreshToken); UserDetails
	 * userDetails = this.userDetailsService.loadUserByUsername(username); Usuario
	 * usuario = usuRepo.findByUsuario(username);
	 * 
	 * if(jwtUtilService.validateToken(refreshToken, userDetails)) { String newJwt =
	 * jwtUtilService.generateToken(userDetails, usuario.getTipo()); String
	 * newRefreshToken = jwtUtilService.generateRefreshToken(userDetails,
	 * usuario.getTipo());
	 * 
	 * 
	 * AuthResponseDto authResponseDto = new AuthResponseDto();
	 * authResponseDto.setToken(newJwt);
	 * authResponseDto.setRefreshToken(newRefreshToken);
	 * 
	 * return new ResponseEntity<>(authResponseDto, HttpStatus.OK); }else { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
	 * }
	 * 
	 * }catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Refresh Token::" +
	 * e.getMessage()); }
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
    @PostMapping(path="/refresh")
    public ResponseEntity<?> refreshUsuario(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        try {
            // Extraer el username del refresh token
            String username = jwtUtilService.extractUsername(refreshToken);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Buscar en la tabla de Empleados
            Empleado empleado = empRepo.findByCorreo(username);

            // Si no es empleado, buscar en la tabla de Huespedes
            Huesped huesped = empleado == null ? huesRepo.findByCorreo(username) : null;

            // Si no existe en ninguna tabla, el usuario no se encuentra
            if (empleado == null && huesped == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
            }

            // Determinar el tipo de usuario según la tabla en la que lo encontramos
            String tipoUsuario = empleado != null ? "Empleado" : "Huesped";

            // Validar el refresh token
            if (jwtUtilService.validateToken(refreshToken, userDetails)) {
                // Generar nuevos tokens JWT
                String newJwt = jwtUtilService.generateToken(userDetails, tipoUsuario);
                String newRefreshToken = jwtUtilService.generateRefreshToken(userDetails, tipoUsuario);

                // Crear la respuesta con los nuevos tokens
                AuthResponseDto authResponseDto = new AuthResponseDto(newJwt, newRefreshToken);
                return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
            }

        } catch (Exception e) {
            // Capturar cualquier error en el proceso de refrescar el token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Refresh Token: " + e.getMessage());
        }
    }
	
	
	
	
	
}
