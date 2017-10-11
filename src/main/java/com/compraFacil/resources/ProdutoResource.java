package com.compraFacil.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compraFacil.domain.Produto;
import com.compraFacil.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {	
		Produto prod = service.buscar(id);
		return ResponseEntity.ok().body(prod);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> find(@RequestBody Produto prod) {	
		prod = service.insert(prod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand( prod.getId() ).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}
