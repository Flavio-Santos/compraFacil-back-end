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

import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Produto;
import com.compraFacil.dto.CategoriaDTO;
import com.compraFacil.dto.ProdutoDTO;
import com.compraFacil.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {	
		Categoria cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> buscar(@RequestBody Categoria cat) {	
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand( cat.getId() ).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//@RequestMapping(method=RequestMethod.GET)
	//public ResponseEntity<List<Categoria>> buscarTodos() {
	//	List<Categoria> list = service.findAll();
		//]List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		//return ResponseEntity.ok().body(listDTO);
	//}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAll(){	
		List<Categoria> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}
