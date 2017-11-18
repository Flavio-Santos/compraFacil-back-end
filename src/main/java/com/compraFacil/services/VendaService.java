package com.compraFacil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Venda;
import com.compraFacil.repositories.VendaRepository;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public List<Venda> buscar(Integer id) {
		
		List<Venda> vendas = vendaRepository.findByVendedor(id);
		if (vendas == null) {
			throw new ObjectNotFoundException("Nenhuma venda no histórico");
		}
		else {
			return vendas;
		}

	}

	public Venda insert(Venda venda) {
		venda.setId(null);
		return vendaRepository.save(venda);
	}
	
	
}
