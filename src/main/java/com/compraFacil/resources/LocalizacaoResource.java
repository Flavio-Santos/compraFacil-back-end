package com.compraFacil.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compraFacil.domain.Localizacao;
import com.compraFacil.services.LocalizacaoService;

@RestController
@RequestMapping(value = "/localizacao")
public class LocalizacaoResource {

	@Autowired
	private LocalizacaoService localizacaoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Localizacao>> findAll() {	
		List<Localizacao> localizacoes = localizacaoService.findAll();
		return ResponseEntity.ok().body(localizacoes);
	}
}
