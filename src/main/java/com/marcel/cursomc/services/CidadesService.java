package com.marcel.cursomc.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcel.cursomc.domain.Cidade;
import com.marcel.cursomc.repositories.CidadeRepository;
import com.marcel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadesService {

	@Autowired
	private CidadeRepository repo;
	
	public Cidade buscar(Integer id) {
		Cidade obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ ", tipo: " +Cidade.class.getName());
		}
		return obj;
	}
}
