package com.marcel.cursomc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcel.cursomc.domain.Cidade;
import com.marcel.cursomc.services.CidadesService;

@RestController
@RequestMapping(value="/cidades")//esse nome será utilizado no browser. No plural, muito parecido com o cakephp
public class CidadeResource {

	@Autowired
	private CidadesService service;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cidade obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
			} 
}
