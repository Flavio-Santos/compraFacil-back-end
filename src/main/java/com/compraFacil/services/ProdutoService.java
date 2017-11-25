package com.compraFacil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Produto;
import com.compraFacil.repositories.ProdutoRepository;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	public Produto buscar(Integer id) {
		Produto prod = repoProduto.findOne(id);
		if (prod == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Produto.class.getName());
		}
		else {
			return prod;
		}
		
	}
	
	public List<Produto> findAll() {
		return repoProduto.findAll();
	}
	
	public List<Produto> buscarPorCategoria(Integer id) {
		List<Produto> prod = repoProduto.findAll();
		List<Produto> prodCat = new ArrayList<Produto>();
		for (Produto produto : prod) {
			if(produto.getCategoria().getId()==id){
				prodCat.add(produto);
			}
		}
		return prodCat;
	}

	public Produto insert(Produto prod) {
		prod.setId(null);
		return repoProduto.save(prod);
	}
}
