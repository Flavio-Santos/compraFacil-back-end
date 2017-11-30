package com.compraFacil.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compraFacil.domain.Produto;
import com.compraFacil.dto.CategoriaDTO;
import com.compraFacil.dto.ProdutoDTO;
import com.compraFacil.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto prod = service.buscar(id);
		CategoriaDTO catDto = new CategoriaDTO(prod.getCategoria());
		ProdutoDTO prodDto = new ProdutoDTO(prod, catDto);
		return ResponseEntity.ok().body(prodDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> find(@RequestBody Produto prod) {
		prod = service.insert(prod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prod.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> findAll(){	
		List<Produto> list = service.findAll();
		List<ProdutoDTO> listDto = list
				.stream()
				.map( obj -> new ProdutoDTO(obj, new CategoriaDTO(obj.getCategoria())) )
				.collect( Collectors.toList() );
		return ResponseEntity.ok().body(listDto);
	}
}
