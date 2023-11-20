package com.hotel.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.demo.interfacesService.IservicioService;
import com.hotel.demo.modelo.Servicio;
import com.hotel.demo.interfaces.IServicio;
@Service
public class ServicioService implements IservicioService{
	@Autowired
	private IServicio data;
	@Override
	public List<Servicio> listarServicio() {
		return (List<Servicio>)data.findAll();
	}

	@Override
	public Optional<Servicio> listarId(int Id_servicio) {
		return data.findById(Id_servicio);
	}

	@Override
	public int Guardar(Servicio s) {
		int res=0;
		Servicio servicio=data.save(s);
		if (!servicio.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void Borrar(int Id_servicio) {
		data.deleteById(Id_servicio);
	}

	@SuppressWarnings("null")
	@Override
	public double obtenerServicioPorId(int Id_servicio) {
		 Optional<Servicio> servicioOptional = listarId(Id_servicio);
		    
		    // Manejar la posibilidad de que el servicio no exista
		    if (servicioOptional.isPresent()) {
		        return servicioOptional.get().getPrecio();
		    } else {
		        // Manejar el caso en que el servicio no existe (lanzar una excepción o devolver null, según tu lógica)
		        return (Double) null;
		    }
	}

	

	
  
}
