package com.compraFacil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.compraFacil.domain.Categoria;
import com.compraFacil.repositories.CategoriaRepository;

@SpringBootApplication
public class CompraFacilApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CompraFacilApplication.class, args);
	
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Veiculo");
		Categoria cat2 = new Categoria(null, "Musica");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
	}
}
