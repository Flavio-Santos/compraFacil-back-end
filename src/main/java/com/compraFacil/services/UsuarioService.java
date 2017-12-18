package com.compraFacil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Cidade;
import com.compraFacil.domain.Endereco;
import com.compraFacil.domain.Usuario;
import com.compraFacil.domain.enums.Perfil;
import com.compraFacil.dto.UsuarioDTO;
import com.compraFacil.dto.UsuarioNewDTO;
import com.compraFacil.repositories.CidadeRepository;
import com.compraFacil.repositories.EnderecoRepository;
import com.compraFacil.repositories.UsuarioRepository;
import com.compraFacil.security.UserSS;
import com.compraFacil.services.exceptions.AuthorizationException;
import com.compraFacil.services.exceptions.DataIntegrityException;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Usuario find(Integer id) {
		//Comentario pro front do flavio funcionar
		/*
		UserSS user = UserService.authenticated();
		if(user == null||!user.hasRole(Perfil.ADMIN)&&id.equals(user.getId())) {
			throw new  AuthorizationException("Acesso Negado");
		}
		*/
		Usuario obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id:"+ id +", "
					+ "tipo: " + Usuario.class.getName());
		}
		
		return obj;
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	public Usuario update(Usuario newUsuario, Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, newUsuario);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há pedidos relacionadas");
		}
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return 	new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usr = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), pe.encode(objDto.getSenha()), objDto.getAvatar());
		System.out.println(objDto.getAvatar());
		//Comentario pro front do flavio funcionar
		//Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		//Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), usr, cid);
		//usr.getEnderecos().add(end);
		//usr.getTelefones().add(objDto.getTelefone1());
		//if(objDto.getTelefone2() != null) {
		//	usr.getTelefones().add(objDto.getTelefone2());
		//}
		//if(objDto.getTelefone3() != null) {
		//	usr.getTelefones().add(objDto.getTelefone3());
		//}
		return usr;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
