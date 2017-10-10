package com.compraFacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Categoria;
import com.compraFacil.repositories.CategoriaRepository;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repoCategoria.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName());
		}
		else {
			return obj;
		}
		
	}
}
