package com.compraFacil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Localizacao;
import com.compraFacil.repositories.LocalizacaoRepository;
import com.compraFacil.services.exceptions.DataIntegrityException;
import com.compraFacil.services.exceptions.ObjectAlreadyExistsException;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class LocalizacaoService {
	
	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	public List<Localizacao> findAll() {
		List<Localizacao> localizacoes = localizacaoRepository.findAll();
		return localizacoes;
	}
	
	public Localizacao find(Integer id) {
		Localizacao obj = localizacaoRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Localizacao.class.getName());
		} 
		
		return obj;
	}
	
	public Localizacao insert(Localizacao loc) {	
		if (verifyLocation(loc)) {
			throw new ObjectAlreadyExistsException("Localizacao já existe no banco de dados !, Tipo: " + Localizacao.class.getName());
		}

		loc.setId(null);
		loc = localizacaoRepository.save(loc);
		return loc;
	}
	
	private boolean verifyLocation(Localizacao loc) {
		if (localizacaoRepository.verifyLocation(loc.getLongitude(), loc.getLatitude()) instanceof Localizacao) {
			return true;
		}
		
		return false;
	}

	public Localizacao update(Localizacao newLoc, Localizacao obj) {
		if (verifyLocation(newLoc)) {
			if (newLoc.getDescricao() == obj.getDescricao()) {
				throw new ObjectAlreadyExistsException("Localizacao já existe no banco de dados !, Tipo: " + Localizacao.class.getName());
			}
		}
		
		obj = find(obj.getId());
		updateData(obj, newLoc);
		return localizacaoRepository.save(obj);
	}
	
	private void updateData(Localizacao newObj, Localizacao obj) {
		newObj.setLatitude(obj.getLatitude());
		newObj.setLongitude(obj.getLongitude());
		newObj.setDescricao(obj.getDescricao());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			localizacaoRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há anuncios relacionadas");
		}
	}
	
	public Page<Localizacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return localizacaoRepository.findAll(pageRequest);
	}
}
