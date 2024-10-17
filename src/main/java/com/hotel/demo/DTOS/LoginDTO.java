package com.hotel.demo.DTOS;

public class LoginDTO {
	
	 private String correo;  // El correo del usuario (Empleado o Huesped)
	    private String clave;   // La contraseña del usuario (sin encriptar)

	    // Constructor vacío
	    public LoginDTO() {}

	    // Constructor con parámetros
	    public LoginDTO(String correo, String clave) {
	        this.correo = correo;
	        this.clave = clave;
	    }

	    // Getters y setters
	    public String getCorreo() {
	        return correo;
	    }

	    public void setCorreo(String correo) {
	        this.correo = correo;
	    }

	    public String getClave() {
	        return clave;
	    }

	    public void setClave(String clave) {
	        this.clave = clave;
	    }

	    @Override
	    public String toString() {
	        return "LoginDTO{" +
	               "correo='" + correo + '\'' +
	               ", clave='" + clave + '\'' +
	               '}';
	    }
	
	
	

}
