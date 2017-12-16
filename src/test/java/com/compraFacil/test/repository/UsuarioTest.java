package com.compraFacil.test.repository;

import org.junit.Test;

import com.compraFacil.domain.Usuario;
import static org.junit.Assert.assertEquals;

public class UsuarioTest {
	
	@Test
	public void naoAddEmailIgual(){
		Usuario usuario1 = new Usuario(1, "Santos", "flavio1@gmail.com", "fsan50", "1234");
		Usuario usuario2 = new Usuario(2, "Santos alves", "flavio@gmail.com", "fsan51", "1234");
		Usuario usuario3 = new Usuario(3, "Santos flavio", "flavio3@gmail.com", "fsan52", "1234");
	
		
	
	}
}
