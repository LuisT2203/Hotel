package com.hotel.demo.controler;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.service.EmpleadoService;
import com.hotel.demo.utils.MensajeResponse;
import com.hotel.demo.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

import com.hotel.demo.DTOS.Detalle_ServicioDTO;
import com.hotel.demo.DTOS.EmpleadoDTO;
import com.hotel.demo.modelo.Detalle_Servicio;
import com.hotel.demo.modelo.Empleado;

@RestController
@RequestMapping(value = "empleado", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ControladorEmpleado {
	@Autowired
	EmpleadoService service;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/lista")
	public ResponseEntity<?> listaEmpleados() throws Exception{
		try {
			List<Empleado> lista = service.listarEmpleado();
			if(lista.size() == 0) {
				return new ResponseEntity<>(
						MensajeResponse.builder()
						.mensaje("No hay empleados")
						.object(null)
						.build(), HttpStatus.NO_CONTENT);
			} else {
				List<EmpleadoDTO> lista2 = lista.stream()
						.map(m -> mapper.map(m, EmpleadoDTO.class))
						.collect(Collectors.toList());
				return new ResponseEntity<> (
						MensajeResponse.builder()
						.mensaje("Si hay registro de Empleados")
						.object(lista2)
						.build(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id_emp}")
    public ResponseEntity<?> obtenerEmpleado(@PathVariable("id_emp") int id_emp) {
        Empleado emp = service.listarId(id_emp);
        if (emp == null) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Empleado No Encontrado").object(null).build(), HttpStatus.NOT_FOUND);
        }
        EmpleadoDTO servicioDTO = mapper.map(emp, EmpleadoDTO.class);
        return new ResponseEntity<>(servicioDTO, HttpStatus.OK);
    }

	@PostMapping("/registrar")
	public ResponseEntity<?> insertarEmpleado(@Valid @RequestBody EmpleadoDTO bean) throws Exception{
		try {
			Empleado emp = mapper.map(bean, Empleado.class);
			Empleado empl = service.Guardar(emp);
			EmpleadoDTO e = mapper.map(empl, EmpleadoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se agrego correctamente el empleado")
					.object(e).build(),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody EmpleadoDTO bean) throws Exception{
		try {
			Empleado emp = mapper.map(bean, Empleado.class);
			Empleado empl = service.Guardar(emp);
			EmpleadoDTO e = mapper.map(empl, EmpleadoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Se Actualizó correctamente el empleado")
					.object(e).build(),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(MensajeResponse.builder().
					mensaje(e.getMessage()).object(null).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDetalleServicio(@PathVariable Integer id) throws Exception{
		try {
            Empleado eliminado = service.Borrar(id);
            if (eliminado == null) {
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("Empleado no encontrado").object(null).build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Eliminado correctamente").object(eliminado).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Error al eliminar").object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
