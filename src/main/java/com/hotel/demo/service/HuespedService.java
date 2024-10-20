package com.hotel.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.demo.interfaces.IHuesped;
import com.hotel.demo.modelo.Huesped;

@Service
public class HuespedService {

	@Autowired
	private IHuesped data;

	public List<Huesped> listarHuesped() {
		return (List<Huesped>) data.findAll();
	}

	public Huesped listarId(int id_huesped) {
		return data.findById(id_huesped).orElse(null);
	}

	public Huesped Guardar(Huesped huesped) {
		return data.save(huesped);
	}

	public Huesped Borrar(int id_huesped) {
		Huesped huesped = data.findById(id_huesped).orElse(null);
		if (huesped != null) {
			data.deleteById(id_huesped);
		}
		return huesped;
	}
}
