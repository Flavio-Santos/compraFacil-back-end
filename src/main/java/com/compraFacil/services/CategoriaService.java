package com.compraFacil.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.dto.UsuarioDTO;
import com.compraFacil.repositories.CategoriaRepository;
import com.compraFacil.services.exceptions.DataIntegrityException;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repoCategoria;

	public Categoria find(Integer id) {
		Categoria obj = repoCategoria.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName());
		} else {
			return obj;
		}
	}

	public List<Categoria> findAll() {
		List<Categoria> lista = repoCategoria.findAll();
		List<Categoria> listaSemAnunciosVendidos = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			listaSemAnunciosVendidos.add(lista.get(i));
			if (lista.get(i).getAnuncios().size() > 0) {
				List<Anuncio> anuncios = new ArrayList<>();
				for (int j = 0; j < lista.get(i).getAnuncios().size(); j++) {
					if (lista.get(i).getAnuncios().get(j).getVendido() == false) {
						anuncios.add(lista.get(i).getAnuncios().get(j));
					}
				}
				listaSemAnunciosVendidos.get(i).setAnuncios(anuncios);
			}
		}
		return listaSemAnunciosVendidos;
	}

	public Categoria insert(Categoria cat) {
		cat.setId(null);
		cat = repoCategoria.save(cat);
		return cat;
	}

	public Categoria update(Categoria newCat, Categoria obj) {
		Categoria newObj = find(obj.getId());
		newObj.setNome(newCat.getNome());
		return repoCategoria.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repoCategoria.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há anuncios relacionadas");
		}
	}
}