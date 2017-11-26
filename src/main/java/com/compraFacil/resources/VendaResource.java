package com.compraFacil.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compraFacil.domain.Venda;
import com.compraFacil.services.VendaService;

@RestController
@RequestMapping(value="/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Venda>> buscarPorVendedor(@PathVariable Integer id) {	
		 List<Venda> vendas = service.buscar(id);
		return ResponseEntity.ok().body(vendas);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Venda venda) {	
		venda = service.insert(venda);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand( venda.getId() ).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
}
