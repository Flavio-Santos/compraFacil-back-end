package com.compraFacil.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Usuario;
import com.compraFacil.dto.AnuncioDTO;
import com.compraFacil.dto.AnuncioNewDTO;
import com.compraFacil.services.AnuncioService;
import com.compraFacil.services.UsuarioService;

@RestController
@RequestMapping(value="/anuncios")
public class AnuncioResource {
	@Autowired
	private AnuncioService service;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AnuncioDTO> find(@PathVariable Integer id) {
		Anuncio obj = service.find(id);
		AnuncioDTO objDto = new AnuncioDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AnuncioNewDTO objDTO) {
		Anuncio obj = service.insert(service.fromDTO(objDTO));
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AnuncioDTO objDTO, @PathVariable Integer id){
		Anuncio obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/comprar/{idComprador}", method = RequestMethod.GET)
	public ResponseEntity<AnuncioDTO> find(@PathVariable Integer id, @PathVariable Integer idComprador) {
		Anuncio anuncio = service.find(id);
		Usuario comprador = usuarioService.find(idComprador);
		service.efetuaVenda(anuncio, comprador);
		AnuncioDTO anuncioDto = new AnuncioDTO(anuncio);
		return ResponseEntity.ok().body(anuncioDto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<AnuncioDTO>> findAll() {
		List<Anuncio> list = service.findAll();
		List<AnuncioDTO> listDTO = list.stream().map(obj -> new AnuncioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity <Page<AnuncioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Anuncio> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AnuncioDTO> listDTO = list.map(obj -> new AnuncioDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	
	}
}
