package com.hotel.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.demo.modelo.Habitacion;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

	@Query("select h from Habitacion h where h.nro_habi=?1")
	public List<Habitacion> listarPorId(int id);
	
}
