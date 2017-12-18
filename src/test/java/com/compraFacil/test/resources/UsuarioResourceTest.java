package com.compraFacil.test.resources;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.compraFacil.dto.UsuarioNewDTO;
import com.compraFacil.resources.AnuncioResource;
import com.compraFacil.services.AnuncioService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioResourceTest {

	@Mock
    private AnuncioService anuncioService;

    @InjectMocks
    private AnuncioResource anuncioResource;
    
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
	public void testGetUsuario() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/usuarios/1"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"cpfOuCnpj\":\"36378912377\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\",\"enderecos\":[{\"id\":3,\"logradouro\":\"Av Afonso\",\"numero\":\"4000\",\"complemento\":null,\"bairro\":\"Nossa Senhora\",\"cep\":\"38777012\",\"cidade\":{\"id\":1,\"nome\":\"Uberlândia\",\"estado\":{\"id\":1,\"nome\":\"Minas Gerais\"}}}],\"telefones\":[\"27363323\",\"88456521\"],\"anunciosCriados\":[{\"id\":2,\"valor\":7932.0,\"nome\":\"CG\",\"descricao\":\"125cc\",\"telefone\":null,\"vendido\":false,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":1,\"latitude\":\"-13151351\",\"longitude\":\"-12132132132\",\"descricao\":\"Em frente ao Mc Donalds\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null},{\"id\":3,\"valor\":500.0,\"nome\":\"notebook i5 usado\",\"descricao\":\"vendo urgente\",\"telefone\":null,\"vendido\":false,\"categoria\":{\"id\":3,\"nome\":\"Eletronicos\"},\"localizacao\":{\"id\":3,\"latitude\":\"-18.909833\",\"longitude\":\"-48.2612146\",\"descricao\":\"Center Shopping\"},\"imagens\":[\"http://i.mlcdn.com.br/1500x1500/notebook-acer-aspire-f5-intel-core-i5-6-geracao8gb-1tb-led-15-6-34-windows-10-216838300.jpg\",\"https://a-static.mlcdn.com.br/1500x1500/notebook-acer-intel-celeron-quad-core-15-6-2-4ghz-windows-10-memoria-ram-4gb-hd-500gb-es1-533-c27u-acer/ldsmobile/5767/6eaba89ea9d94863a5eb6c9406d2e8e1.jpg\"],\"propriedades\":[{\"nome\":\"ano\",\"valor\":\"2015\"},{\"nome\":\"processador\",\"valor\":\"i5\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null}],\"perfis\":[\"USUARIO\"]}";
		
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void testGetAllUsuarios() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/usuarios"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "[{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},{\"id\":2,\"nome\":\"flavio\",\"email\":\"asdasd@gmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},{\"id\":3,\"nome\":\"ariel\",\"email\":\"a@a.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"}]";
		
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void toTestLastAddUser() {		
		UsuarioNewDTO newUserDto = new UsuarioNewDTO();
		newUserDto.setNome("user test");
		newUserDto.setEmail("addtest@test.com");
		newUserDto.setSenha("123");

		HttpEntity<UsuarioNewDTO> entity = new HttpEntity<UsuarioNewDTO>(newUserDto, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/usuarios"),
				HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.CREATED);
		assertTrue(actual.contains("/usuarios/"));
	}
	
	@Test
	public void toTestLastDeleteUserWithoutAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/usuarios/3"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	@Test
	public void toTestLastDeleteUserWithAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/usuarios/1"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
