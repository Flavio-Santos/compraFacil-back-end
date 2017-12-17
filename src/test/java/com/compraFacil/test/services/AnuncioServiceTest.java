package com.compraFacil.test.services;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.services.AnuncioService;
import com.compraFacil.services.CategoriaService;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AnuncioServiceTest {

	@Autowired
    private AnuncioService service;
	@Autowired
    private CategoriaService catService;
    
    @Test
    public void testFindAll() {
        List<Anuncio> list = service.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 3, list.size());
    }
    
    @Test
    public void testFindOne() {
        Integer id = 1;
        Anuncio anun = service.find(id);
        
        Assert.assertNotNull("failure - expected not null", anun);
        Assert.assertEquals("failure - expected id attribute match", id, anun.getId());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testFindOneNotFound() {
    	Integer id = Integer.MAX_VALUE;
    	
        @SuppressWarnings("unused")
		Anuncio anun = service.find(id);
    }

    @Test
    public void testInsert() {
        Categoria cat1 = new Categoria(null, "Veiculo");
        catService.insert(cat1);
        
        Anuncio anuncio1 = new Anuncio(null, 395.0, "Bicleta verde", "semi nova", null, null, null, null, null, null, cat1); 
        
        Anuncio createdEntity = service.insert(anuncio1);
        
        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
        Assert.assertEquals("failure - expected nome attribute to match", "Bicleta verde", createdEntity.getNome());
        Assert.assertEquals("failure - expected descricao attribute to match", "semi nova", createdEntity.getDescricao());
        Assert.assertEquals("failure - expected categoria attribute to match", cat1, createdEntity.getCategoria());
        Assert.assertEquals("failure - expected valor attribute to match", 395.0, createdEntity.getValor(), 0.001);   
        
        List<Anuncio> list = service.findAll();
        Assert.assertEquals("failure - expected size", 4, list.size());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testCreateWithId() {
        Anuncio anun = new Anuncio();
        anun.setId(Integer.MAX_VALUE);
        anun.setNome("test");

        service.insert(anun);
    }

    
    @Test
    public void testUpdate() {
    	Integer id = 1;
        Anuncio anun = service.find(id);

        Assert.assertNotNull("failure - expected not null", anun);

        String updateDesc = anun.getNome() + " - test";
        anun.setNome(updateDesc);
        
        Anuncio updatedAnuncio = service.update(anun);

        Assert.assertNotNull("failure - expected not null", updatedAnuncio);
        Assert.assertEquals("failure - expected id attribute to match", id, updatedAnuncio.getId());
        Assert.assertEquals("failure - expected descricao attribute to match", updateDesc, updatedAnuncio.getNome());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testDelete() {
        Integer id = 1;
        Anuncio anun = service.find(id);
        Assert.assertNotNull("failure - expected not null", anun);

        service.delete(id);
        service.find(id);
    }

}


