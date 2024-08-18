package com.hotel.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.interfaces.IServicio;
@Service
public class ServicioService {
	@Autowired
	private IServicio data;
	
	public List<Servicio> listarServicio() {
		return (List<Servicio>)data.findAll();
	}

	
	public Servicio listarId(int Id_servicio) {
		return data.findById(Id_servicio).orElse(new Servicio());
	}

	
	public Servicio Guardar(Servicio s) {
		return data.save(s);
		
	}

	
	public Servicio Borrar(int Id_servicio) {
		Servicio temp = data.findById(Id_servicio).orElse(null);
		if(temp==null) {
			return new Servicio();
		}else {
			data.deleteById(Id_servicio);
			return temp;
		}
		
	}

	public Optional<Servicio> listarId1(int Id_servicio) {
		return data.findById(Id_servicio);
	}
	

	

	
  
}
