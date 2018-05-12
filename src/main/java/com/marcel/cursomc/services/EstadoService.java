package com.marcel.cursomc.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcel.cursomc.domain.Estado;
import com.marcel.cursomc.repositories.EstadoRepository;
import com.marcel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public Estado buscar(Integer id) {
		Estado obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ ", tipo: " +Estado.class.getName());
		}
		return obj;
	}
}
