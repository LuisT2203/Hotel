package com.hotel.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IServicio;
import com.hotel.demo.modelo.Servicio;

@Service
public class ServicioService {

	@Autowired
	private IServicio data;

	public List<Servicio> listarServicio() {
		return (List<Servicio>) data.findAll();
	}

	public Servicio listarId(int id_servicio) {
		return data.findById(id_servicio).orElse(null);
	}

	public Servicio Guardar(Servicio servicio) {
		return data.save(servicio);
	}

	public Servicio Borrar(int id_servicio) {
		Servicio servicio = data.findById(id_servicio).orElse(null);
		if (servicio != null) {
			data.deleteById(id_servicio);
		}
		return servicio;
	}
}