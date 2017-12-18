package com.compraFacil.test.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.ArrayEquals;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Usuario;
import com.compraFacil.dto.AnuncioDTO;
import com.compraFacil.dto.AnuncioNewDTO;
import com.compraFacil.dto.UsuarioNewDTO;
import com.compraFacil.resources.AnuncioResource;
import com.compraFacil.services.AnuncioService;
import com.compraFacil.services.TesteService;
import com.compraFacil.services.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AnuncioResourceTest {

	/*@Autowired
	private MockMvc mockMvc;*/
	
	@Autowired
	@Mock
    private AnuncioService anuncioService;

    @Autowired
    @InjectMocks
    private AnuncioResource anuncioResource;

    @Autowired
    private TesteService tService;
    @Autowired
    private UsuarioService usuarioService;
    
    @Before
    public void setUp() throws Exception {
    	//tService.instantiateTestDatabase();
    }

    private int port = 8080;
    
	private TestRestTemplate restTemplate = new TestRestTemplate();

	private HttpHeaders headers = new HttpHeaders();
	
	private HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void testGetAnuncio() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anuncios/3"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "{\"id\": 3,\"valor\": 500,\"nome\": \"notebook i5 usado\",\"descricao\": \"vendo urgente\",\"vendido\": false,\"telefone\": null,\"categoria\": {\"id\": 3,\"nome\": \"Eletronicos\"},\"localizacao\": {\"id\": 3,\"latitude\": \"-18.909833\",\"longitude\": \"-48.2612146\",\"descricao\": \"Center Shopping\"},\"imagens\": [\"http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-f5-intel-core-i5-6-geracao8gb-1tb-led-15-6-34-windows-10-216838300.jpg\",\"https://a-static.mlcdn.com.br/1500x1500/notebook-acer-intel-celeron-quad-core-15-6-2-4ghz-windows-10-memoria-ram-4gb-hd-500gb-es1-533-c27u-acer/ldsmobile/5767/6eaba89ea9d94863a5eb6c9406d2e8e1.jpg\"],\"propriedades\": [{\"nome\": \"ano\",\"valor\": \"2015\"},{\"nome\": \"processador\",\"valor\": \"i5\"}],\"dataCriacao\": \"18/12/2017 05:06\",\"dataFechamento\": null,\"vendedor\": {\"id\": 1,\"nome\": \"santana\",\"email\": \"santana@hotmail.com\",\"avatar\": \"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\": {\"id\": 2,\"nome\": \"flavio\",\"email\": \"asdasd@gmail.com\",\"avatar\": \"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"}}";

		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void testGetAllAnuncios() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anuncios"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "[{\"id\":1,\"valor\":490.0,\"nome\":\"Bicleta azul\",\"descricao\":\"semi nova\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":2,\"latitude\":\"-48648648\",\"longitude\":\"-6845684684\",\"descricao\":\"Em frente ao Burguer King\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":null,\"comprador\":null},{\"id\":2,\"valor\":7932.0,\"nome\":\"CG\",\"descricao\":\"125cc\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":1,\"latitude\":\"-13151351\",\"longitude\":\"-12132132132\",\"descricao\":\"Em frente ao Mc Donalds\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\":null},{\"id\":3,\"valor\":500.0,\"nome\":\"notebook i5 usado\",\"descricao\":\"vendo urgente\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":3,\"nome\":\"Eletronicos\"},\"localizacao\":{\"id\":3,\"latitude\":\"-18.909833\",\"longitude\":\"-48.2612146\",\"descricao\":\"Center Shopping\"},\"imagens\":[\"http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-f5-intel-core-i5-6-geracao8gb-1tb-led-15-6-34-windows-10-216838300.jpg\",\"https://a-static.mlcdn.com.br/1500x1500/notebook-acer-intel-celeron-quad-core-15-6-2-4ghz-windows-10-memoria-ram-4gb-hd-500gb-es1-533-c27u-acer/ldsmobile/5767/6eaba89ea9d94863a5eb6c9406d2e8e1.jpg\"],\"propriedades\":[{\"nome\":\"ano\",\"valor\":\"2015\"},{\"nome\":\"processador\",\"valor\":\"i5\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\":{\"id\":2,\"nome\":\"flavio\",\"email\":\"asdasd@gmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"}}]";
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void testGetAllAnunciosByUsuario() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anuncios/usuario/1"),HttpMethod.GET, httpEntity, String.class);
		
		String actual = response.getBody();
		
		String expected = "[{\"id\":2,\"valor\":7932.0,\"nome\":\"CG\",\"descricao\":\"125cc\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":1,\"latitude\":\"-13151351\",\"longitude\":\"-12132132132\",\"descricao\":\"Em frente ao Mc Donalds\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\":null},{\"id\":3,\"valor\":500.0,\"nome\":\"notebook i5 usado\",\"descricao\":\"vendo urgente\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":3,\"nome\":\"Eletronicos\"},\"localizacao\":{\"id\":3,\"latitude\":\"-18.909833\",\"longitude\":\"-48.2612146\",\"descricao\":\"Center Shopping\"},\"imagens\":[\"http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-f5-intel-core-i5-6-geracao8gb-1tb-led-15-6-34-windows-10-216838300.jpg\",\"https://a-static.mlcdn.com.br/1500x1500/notebook-acer-intel-celeron-quad-core-15-6-2-4ghz-windows-10-memoria-ram-4gb-hd-500gb-es1-533-c27u-acer/ldsmobile/5767/6eaba89ea9d94863a5eb6c9406d2e8e1.jpg\"],\"propriedades\":[{\"nome\":\"ano\",\"valor\":\"2015\"},{\"nome\":\"processador\",\"valor\":\"i5\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\":{\"id\":2,\"nome\":\"flavio\",\"email\":\"asdasd@gmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"}}]";
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, actual, true);
	}
	
	/*@Transactional
	@Test
	public void addCourse() {

		{
		//	"valor": 600.0,
		//	"nome" : "celular j5",
		//	"categoria" : {
		//		"id" : 3
		//	},
		//	"localizacao" : {
		//		"id" : 1
		//	},
		//	"descricao" : "vendo celular usado",
		//	"vendedor" : {
		//		"id": 2
		//	}
		//}
		
		Categoria categoria = new Categoria();
		categoria.setId(3);
		
		Usuario vendedor = new Usuario();
		Usuario usr1 = usuarioService.find(2);
		
		AnuncioNewDTO anuncio = new AnuncioNewDTO();
		anuncio.setValor(600.0);
		anuncio.setNome("celular j5");
		anuncio.setCategoria(categoria);
		anuncio.setVendedor(usr1);
		anuncio.setDescricao("vendo celular usado");

		HttpEntity<AnuncioNewDTO> entity = new HttpEntity<AnuncioNewDTO>(anuncio, headers);

		ResponseEntity<AnuncioNewDTO> response = restTemplate.exchange(
				createURLWithPort("/anuncios"),
				HttpMethod.POST, entity, AnuncioNewDTO.class);

		System.out.println(response);

		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/anuncios/"));

	}*/
   
}
