package com.compraFacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Usuario;
import com.compraFacil.repositories.UsuarioRepository;
import com.compraFacil.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usr = repo.findByEmail(email);
		if(usr == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(usr.getId(), usr.getEmail(), usr.getSenha(), usr.getPerfis());
	}

}
