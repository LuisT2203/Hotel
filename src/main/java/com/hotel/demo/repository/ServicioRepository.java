package com.hotel.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.demo.modelo.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

	@Query("select s from Servicio s where s.id_servicio=?1")
	public List<Servicio> listarPorId(int id);
	
}
