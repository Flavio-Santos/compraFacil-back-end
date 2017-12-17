package com.compraFacil.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Endereco;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Usuario;
import com.compraFacil.dto.AnuncioDTO;
import com.compraFacil.dto.AnuncioNewDTO;
import com.compraFacil.repositories.AnuncioRepository;
import com.compraFacil.services.exceptions.DataIntegrityException;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository repoAnuncio;
	@Autowired
	private UsuarioService usuarioService;

	public Anuncio find(Integer id) {
		Anuncio anuncio = repoAnuncio.findOne(id);
		if (anuncio == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Anuncio.class.getName());
		} else {
			return anuncio;
		}
	}
	
	public Anuncio efetuaVenda(Anuncio obj, Usuario comprador) {
		Anuncio anuncio = find(obj.getId());
		anuncio.efetuaVenda(comprador);
		return repoAnuncio.save(anuncio);
	}
	
	/*public Anuncio insert(Anuncio obj) {
		obj.setId(null);
		obj = repoAnuncio.save(obj);
		//enderecoRepository.save(obj.getEnderecos());
		return obj;
	}*/
	
	public Anuncio insert(Anuncio obj) {
		
		if (obj == null) {
			throw new ObjectNotFoundException("Erro no anuncio: Anuncio vazio!, Tipo: " + Anuncio.class.getName());
		}
		if (obj.getVendedor() == null) {
			throw new ObjectNotFoundException("Erro no anuncio!: Sem Vendedor!, Tipo: " + Anuncio.class.getName());
		}
		obj.setId(null);
		obj.setDataCriacao(new Date());
		//enderecoRepository.save(obj.getEnderecos());
		return repoAnuncio.save(obj);
		
	}
	public Anuncio update(Anuncio obj) {
		Anuncio newObj = find(obj.getId());
		updateData(newObj, obj);
		return repoAnuncio.save(newObj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repoAnuncio.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há pedidos relacionadas");
		}
	}
	
	public List<Anuncio> findAll() {
		return repoAnuncio.FindAllNotSold();
	}

	public List<Anuncio> buscarPorCategoria(Integer id) {
		List<Anuncio> prod = repoAnuncio.findAll();
		List<Anuncio> prodCat = new ArrayList<Anuncio>();
		for (Anuncio anuncio : prod) {
			if (anuncio.getCategoria().getId() == id) {
				prodCat.add(anuncio);
			}
		}
		return prodCat;
	}
	
	public Page<Anuncio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repoAnuncio.findAll(pageRequest);
	}
	public Anuncio fromDTO(AnuncioDTO objDto) {
		return 	new Anuncio();
	}
	public Anuncio fromDTO(AnuncioNewDTO objDto) {
		Anuncio anuncio = new Anuncio(
				objDto.getId(), 
				objDto.getValor(), 
				objDto.getNome(), 
				objDto.getDescricao(), 
				objDto.getTelefone(), 
				objDto.getDataCriacao(), 
				objDto.getDataFechamento(), 
				objDto.getComprador(), 
				objDto.getVendedor(),
				objDto.getLocalizacao(),
				objDto.getCategoria()
		);
		//Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());

		anuncio.getImagens().add(objDto.getImagem1());
		if(objDto.getImagem2() != null) {
			anuncio.getImagens().add(objDto.getImagem2());
		}
		if(objDto.getImagem3() != null) {
			anuncio.getImagens().add(objDto.getImagem3());
		}
		
		return anuncio;
	}
	
	private void updateData(Anuncio newObj, Anuncio obj) {
		//newObj.setNome(obj.getNome());
		//newObj.setEmail(obj.getEmail());
	}

	public List<Anuncio> findAnunciosByVendedorId(Integer id) {
		List<Anuncio> prod = repoAnuncio.findAll();
		List<Anuncio> prodUsr = new ArrayList<Anuncio>();
		for (Anuncio anuncio : prod) {
			if (anuncio.getVendedor().getId() == id) {
				prodUsr.add(anuncio);
			}
		}
		return prodUsr;
	}
}