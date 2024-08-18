package com.hotel.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hotel.demo.interfaces.IHuesped;

import com.hotel.demo.modelo.Huesped;
@Service
public class HuespedService{
	@Autowired
	private IHuesped data;
	
	public List<Huesped> listarHuesped() {
		return (List<Huesped>)data.findAll();
	}

	
	public Huesped listarId(int Id_huesped) {
		// TODO Auto-generated method stub
		return data.findById(Id_huesped).orElse(new Huesped());
	}

	
	public Huesped Guardar(Huesped h) {
		return data.save(h);
	}

	
	public Huesped Borrar(int Id_huesped) {
		Huesped temp = data.findById(Id_huesped).orElse(null);
		if(temp==null) {
			return new Huesped();
		}else {
			data.deleteById(Id_huesped);
			return temp;
		}
		
	}

}
