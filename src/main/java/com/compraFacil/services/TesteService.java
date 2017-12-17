package com.compraFacil.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Cidade;
import com.compraFacil.domain.Endereco;
import com.compraFacil.domain.Estado;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Propriedade;
import com.compraFacil.domain.Usuario;
import com.compraFacil.domain.enums.Perfil;
import com.compraFacil.repositories.AnuncioRepository;
import com.compraFacil.repositories.CategoriaRepository;
import com.compraFacil.repositories.CidadeRepository;
import com.compraFacil.repositories.EnderecoRepository;
import com.compraFacil.repositories.EstadoRepository;
import com.compraFacil.repositories.LocalizacaoRepository;
import com.compraFacil.repositories.PropriedadeRepository;
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
	private PropriedadeRepository propriedadeRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() {

		Localizacao loc1 = new Localizacao(null, "-13151351", "-12132132132", "Em frente ao Mc Donalds");
		Localizacao loc2 = new Localizacao(null, "-48648648", "-6845684684", "Em frente ao Burguer King");
		Localizacao loc3 = new Localizacao(null, "-18.909833", "-48.2612146", "Center Shopping");
		localizacaoRepository.save(Arrays.asList(loc1, loc2, loc3));
		
		Usuario usr1 = new Usuario(null, "santana", "santana@hotmail.com", "36378912377", pe.encode("afafoo"));
		usr1.getTelefones().addAll(Arrays.asList("27363323", "88456521"));

		Usuario usr2 = new Usuario(null, "flavio", "asdasd@gmail.com", "53243491193", pe.encode("opop777"));
		usr2.getTelefones().addAll(Arrays.asList("32992288", "923723423"));
		usr2.addPerfil(Perfil.ADMIN);
		
		Usuario usr3 = new Usuario(null, "ariel", "a@a.com", null, pe.encode("a"));
		
		usuarioRepository.save(Arrays.asList(usr1, usr2, usr3));
		
		Categoria cat1 = new Categoria(null, "Veiculo");
		Categoria cat2 = new Categoria(null, "Musica");
		Categoria cat3 = new Categoria(null, "Eletronicos");

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		
		Anuncio anuncio1 = new Anuncio(null, 490.0, "Bicleta azul", "semi nova", null, new Date(), null, null, null, loc2, cat1);
		Anuncio anuncio2 = new Anuncio(null, 7932.0, "CG", "125cc", null, new Date(), null, null, null, loc1, cat1);
		//Anuncio anuncio4 = new Anuncio(id, valor,    nome, descricao, telefone, dataCriacao, dataFechamento, comprador, vendedor, localizacao, categoria);
		Anuncio anuncio3 = new Anuncio(null, 500.0, "notebook i5 usado", "vendo urgente", null, new Date(), null, usr2, usr1, loc3, cat3);
		anuncio3.getImagens().addAll(Arrays.asList("http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-f5-intel-core-i5-6-geracao8gb-1tb-led-15-6-34-windows-10-216838300.jpg", "https://a-static.mlcdn.com.br/1500x1500/notebook-acer-intel-celeron-quad-core-15-6-2-4ghz-windows-10-memoria-ram-4gb-hd-500gb-es1-533-c27u-acer/ldsmobile/5767/6eaba89ea9d94863a5eb6c9406d2e8e1.jpg"));
		
		Propriedade prop1 = new Propriedade(anuncio3.getId(), "ano", "2015", anuncio3);
		Propriedade prop2 = new Propriedade(anuncio3.getId(), "processador", "i5", anuncio3);
		Propriedade prop3 = new Propriedade(anuncio3.getId(), "cor", "azul", anuncio1);
		Propriedade prop4 = new Propriedade(anuncio3.getId(), "cor", "azul", anuncio2);
		
		anuncioRepository.save(Arrays.asList(anuncio1, anuncio2, anuncio3));
		propriedadeRepository.save(Arrays.asList(prop1, prop2, prop3, prop4));

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
