package com.compraFacil.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Cidade;
import com.compraFacil.domain.Endereco;
import com.compraFacil.domain.Estado;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Usuario;
import com.compraFacil.domain.enums.Perfil;
import com.compraFacil.repositories.AnuncioRepository;
import com.compraFacil.repositories.CategoriaRepository;
import com.compraFacil.repositories.CidadeRepository;
import com.compraFacil.repositories.EnderecoRepository;
import com.compraFacil.repositories.EstadoRepository;
import com.compraFacil.repositories.LocalizacaoRepository;
import com.compraFacil.repositories.UsuarioRepository;

@Service
public class TesteService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private AnuncioRepository anuncioRepository;
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() {

		Localizacao loc1 = new Localizacao(null, "-13151351", "-12132132132", "Em frente ao Mc Donalds");
		Localizacao loc2 = new Localizacao(null, "-48648648", "-6845684684", "Em frente ao Burguer King");

		localizacaoRepository.save(Arrays.asList(loc1, loc2));
		
		Usuario usr1 = new Usuario(null, "santana", "santana@hotmail.com", "36378912377", pe.encode("afafoo"));
		usr1.getTelefones().addAll(Arrays.asList("27363323", "88456521"));

		Usuario usr2 = new Usuario(null, "flavio", "asdasd@gmail.com", "53243491193", pe.encode("opop777"));
		usr2.getTelefones().addAll(Arrays.asList("32992288", "923723423"));
		usr2.addPerfil(Perfil.ADMIN);
		
		usuarioRepository.save(Arrays.asList(usr1, usr2));

		
		Categoria cat1 = new Categoria(null, "Veiculo");
		Categoria cat2 = new Categoria(null, "Musica");
		Categoria cat3 = new Categoria(null, "Eletronicos");

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		
		//Anuncio anuncio1 = new Anuncio(null, 8600.0, "Bicleta azul", "semi nova", cat1);
		//Anuncio anuncio2 = new Anuncio(null, 7932.0, "CG", "125cc", cat3);
		Anuncio anuncio3 = new Anuncio(null, 500.0, "notebook do flavio estragado", "vendo urgente", null, null, null, null, usr1, null, cat3);
		
		anuncioRepository.save(Arrays.asList(anuncio3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", usr2, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "sala 800", "Centro", "38777012", usr2, c2);
		Endereco e3 = new Endereco(null, "Av Afonso", "4000", null, "Nossa Senhora", "38777012", usr1, c1);

		usr1.getEnderecos().addAll(Arrays.asList(e1, e2));
		usr2.getEnderecos().addAll(Arrays.asList(e3));

		enderecoRepository.save(Arrays.asList(e1, e2, e3));

	}
}
