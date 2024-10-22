package com.hotel.demo.service;

import java.util.List;

public interface ICRUD<T,ID> {
	T registrar(T bean) throws Exception;
	T actualizar(T bean) throws Exception;
	void eliminar(ID cod) throws Exception;
	T buscar(ID cod) throws Exception;
	List<T> listar() throws Exception;
	
 
}
