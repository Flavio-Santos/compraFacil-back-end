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

import com.compraFacil.domain.Categoria;
import com.compraFacil.resources.AnuncioResource;
import com.compraFacil.services.AnuncioService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaResourceTest {

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
	public void testGetCategoria() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categorias/1"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "{\"id\":1,\"nome\":\"Veiculo\",\"anuncios\":[{\"id\":1,\"valor\":490.0,\"nome\":\"Bicleta azul\",\"descricao\":\"semi nova\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":2,\"latitude\":\"-48648648\",\"longitude\":\"-6845684684\",\"descricao\":\"Em frente ao Burguer King\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":null,\"comprador\":null},{\"id\":2,\"valor\":7932.0,\"nome\":\"CG\",\"descricao\":\"125cc\",\"vendido\":false,\"telefone\":null,\"categoria\":{\"id\":1,\"nome\":\"Veiculo\"},\"localizacao\":{\"id\":1,\"latitude\":\"-13151351\",\"longitude\":\"-12132132132\",\"descricao\":\"Em frente ao Mc Donalds\"},\"imagens\":[],\"propriedades\":[{\"nome\":\"cor\",\"valor\":\"azul\"}],\"dataCriacao\":\"18/12/2017 05:06\",\"dataFechamento\":null,\"vendedor\":{\"id\":1,\"nome\":\"santana\",\"email\":\"santana@hotmail.com\",\"avatar\":\"http://www.nightlife.ca/assets/images/default/user_picture_default.png\"},\"comprador\":null}]}";
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void testGetAllCategorias() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categorias"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "[{\"id\":1,\"nome\":\"Veiculo\"},{\"id\":2,\"nome\":\"Musica\"},{\"id\":3,\"nome\":\"Eletronicos\"}]"; 
			
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void toTestLastAddCategoria() {		
		Categoria categoria = new Categoria(null , "jogos");

		HttpEntity<Categoria> entity = new HttpEntity<Categoria>(categoria, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/categorias"),
				HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.CREATED);
		assertTrue(actual.contains("/categorias/"));
	}
	
	@Test
	public void toTestLastDeleteCategoriaWithoutAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categorias/2"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	@Test
	public void toTestLastDeleteCategoriaWithAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categorias/1"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

}
