package com.compraFacil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Localizacao;
import com.compraFacil.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {
	
	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	public List<Localizacao> findAll() {
		List<Localizacao> localizacoes = localizacaoRepository.findAll();
		return localizacoes;
	}
	
	
}
