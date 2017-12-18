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

import com.compraFacil.domain.Localizacao;
import com.compraFacil.resources.AnuncioResource;
import com.compraFacil.services.AnuncioService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalizacaoResourceTest {

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
	public void testGetLocalizacao() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/localizacao/2"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "{\"id\":2,\"latitude\":\"-48648648\",\"longitude\":\"-6845684684\",\"descricao\":\"Em frente ao Burguer King\"}";
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void testGetAllLocalizacoes() throws JSONException {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/localizacao"),HttpMethod.GET, httpEntity, String.class);
		
		String expected = "[{\"id\":1,\"latitude\":\"-13151351\",\"longitude\":\"-12132132132\",\"descricao\":\"Em frente ao Mc Donalds\"},{\"id\":2,\"latitude\":\"-48648648\",\"longitude\":\"-6845684684\",\"descricao\":\"Em frente ao Burguer King\"},{\"id\":3,\"latitude\":\"-18.909833\",\"longitude\":\"-48.2612146\",\"descricao\":\"Center Shopping\"},{\"id\":4,\"latitude\":\"-18.999125\",\"longitude\":\"-49.5657846\",\"descricao\":\"Uberlandia Shopping\"}]";
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}
	
	@Test
	public void toTestLastAddLocalizacao() {		
		Localizacao loc = new Localizacao();
		loc.setLatitude("-486418648");
		loc.setLongitude("-6845456846411484");
		loc.setDescricao("uberlandia shopping");

		HttpEntity<Localizacao> entity = new HttpEntity<Localizacao>(loc, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/localizacao"),
				HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.CREATED);
		assertTrue(actual.contains("/localizacao/"));
	}
	
	@Test
	public void toTestLastDeleteLocalizacaoWithoutAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/localizacao/4"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	@Test
	public void toTestLastDeleteLocalizacaoWithAnuncios() {		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/localizacao/1"),HttpMethod.DELETE, httpEntity, String.class);
		
		Assert.assertEquals("failure - expected status code to match", response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
