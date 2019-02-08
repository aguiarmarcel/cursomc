package com.marcel.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcel.cursomc.domain.Categoria;
import com.marcel.cursomc.domain.Estado;
import com.marcel.cursomc.dto.EstadoDTO;
import com.marcel.cursomc.repositories.EstadoRepository;
import com.marcel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public Estado find(Integer id) {
		Estado obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto Estado não encontrado! Id:" + id 
					+ ", tipo: " +Categoria.class.getName());
		}
		return obj;
	}
	
	public List<Estado> findAll(){
		return repo.findByOrderByName();
	}
	
	public Page<Estado> findPage(Integer page, Integer LinesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, LinesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Estado fromDTO(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getNome());
	}
}
