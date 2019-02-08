package com.marcel.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcel.cursomc.domain.Cidade;
import com.marcel.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEStado(Integer estadoId){
		return repo.findCidades(estadoId);
	}
}
