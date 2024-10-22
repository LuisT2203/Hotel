package com.hotel.demo.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IHabitacion;



import com.hotel.demo.modelo.Habitacion;


@Service
public class HabitacionService  {

	@Autowired
	private IHabitacion data;
	
	
	public List<Habitacion> listarHabitacion() {
		return (List<Habitacion>)data.findAll();
	}

	
	public Habitacion listarNro(int Nro_habi) {
		// TODO Auto-generated method stub
		return data.findById(Nro_habi).orElse(new Habitacion());
	}

	
	public Habitacion Guardar(Habitacion h) {
		return data.save(h);
			
	}

	
	public Habitacion Borrar(int Nro_habi) {
		
		Habitacion temp = data.findById(Nro_habi).orElse(null);
		if(temp==null) {
			return new Habitacion();
		}else {
			data.deleteById(Nro_habi);
			return temp;
		}
		
		
	}

}
