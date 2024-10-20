package com.hotel.demo.controler;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.DTOS.ServicioDTO;
import com.hotel.demo.modelo.Servicio;

import com.hotel.demo.service.ServicioService;
import com.hotel.demo.utils.MensajeResponse;

import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "servicio", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorServicio {
	@Autowired
	private ServicioService service;
	
	@Autowired
    private ModelMapper mapper;

    @GetMapping("/lista")
    public ResponseEntity<?> listarServicios() {
        List<Servicio> lista = service.listarServicio();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("No hay registros").object(null).build(), HttpStatus.OK);
        } else {
            List<ServicioDTO> listaDTO = lista.stream()
                    .map(s -> mapper.map(s, ServicioDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Si hay registros").object(listaDTO).build(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id_servicio}")
    public ResponseEntity<?> obtenerServicio(@PathVariable("id_servicio") int id_servicio) {
        Servicio servicio = service.listarId(id_servicio);
        if (servicio == null) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Servicio no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
        }
        ServicioDTO servicioDTO = mapper.map(servicio, ServicioDTO.class);
        return new ResponseEntity<>(servicioDTO, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> insertarServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        try {
            Servicio servicio = mapper.map(servicioDTO, Servicio.class);
            Servicio nuevoServicio = service.Guardar(servicio);
            ServicioDTO responseDTO = mapper.map(nuevoServicio, ServicioDTO.class);
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Guardado correctamente").object(responseDTO).build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al guardar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        try {
            Servicio servicio = mapper.map(servicioDTO, Servicio.class);
            Servicio actualizado = service.Guardar(servicio);
            ServicioDTO responseDTO = mapper.map(actualizado, ServicioDTO.class);
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Actualizado correctamente").object(responseDTO).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al actualizar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id_servicio}")
    public ResponseEntity<?> eliminar(@PathVariable("id_servicio") int id_servicio) {
        try {
            Servicio eliminado = service.Borrar(id_servicio);
            if (eliminado == null) {
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("Servicio no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
