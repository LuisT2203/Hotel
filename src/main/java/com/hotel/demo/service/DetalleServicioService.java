package com.hotel.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfaces.IDetalleServicio;

import com.hotel.demo.modelo.Detalle_Servicio;
import com.hotel.demo.modelo.Habitacion;

@Service
public class DetalleServicioService  {
	@Autowired
	private IDetalleServicio data;
	
	
	
	public List<Detalle_Servicio> listarDetServicio() {
		return (List<Detalle_Servicio>)data.findAll();
	}


	public Detalle_Servicio listarId(int Id_detservicio) {
		return data.findById(Id_detservicio).orElse(new Detalle_Servicio());
	}

	
	public Detalle_Servicio Guardar(Detalle_Servicio ds) {
		return data.save(ds);
		
	}

	
	public Detalle_Servicio Borrar(int Id_detservicio) {
		Detalle_Servicio temp = data.findById(Id_detservicio).orElse(null);
		if(temp==null) {
			return new Detalle_Servicio();
		}else {
			data.deleteById(Id_detservicio);
			return temp;
		}
		
		
	}

}
