package com.compraFacil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Produto;
import com.compraFacil.domain.Usuario;
import com.compraFacil.repositories.CategoriaRepository;
import com.compraFacil.repositories.LocalizacaoRepository;
import com.compraFacil.repositories.ProdutoRepository;


@SpringBootApplication
public class CompraFacilApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CompraFacilApplication.class, args);
	
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Veiculo");
		Categoria cat2 = new Categoria(null, "Musica");
		Categoria cat3 = new Categoria(null, "Eletronicos");
		Categoria cat4 = new Categoria(null, "Musica");
		Categoria cat5 = new Categoria(null, "Musica");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		
		Produto prod1 = new Produto(null, 249.0, "Bicleta azul", "vendo bicicleta show", cat1);

		produtoRepository.save(Arrays.asList(prod1));
		
		Localizacao loc1 = new Localizacao(null, "-13151351", "-12132132132", "Em frente ao Mc Donalds");
		Localizacao loc2 = new Localizacao(null, "-48648648", "-6845684684", "Em frente ao Burguer King");
		
		localizacaoRepository.save(Arrays.asList(loc1, loc2));
		
		Usuario user1 = new Usuario(null, "flavio", "asdasd@gmail.com", "fsan", "afafoo");
		Usuario user2 = new Usuario(null, "santana", "santana@hotmail.com", "saint", "opop777");
		

		Produto prod2 = new Produto(null, 249.0, "TV LED 40", "TV de 40 polegadas", cat3);
		Produto prod3 = new Produto(null, 249.0, "Violão Gianini", "Violão seminovo", cat2);
		Produto prod4 = new Produto(null, 249.0, "iPhone 5s", "", cat3);
		Produto prod5 = new Produto(null, 249.0, "Amplificador Orange 20w valvulado", "Apenas 3 meses de uso, e vai com a caixa", cat2);
		
		produtoRepository.save(Arrays.asList(prod1, prod2, prod3, prod4, prod5));

	
	}
}
