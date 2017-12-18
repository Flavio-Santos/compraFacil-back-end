package com.compraFacil.test.services;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.compraFacil.domain.Localizacao;
import com.compraFacil.services.LocalizacaoService;
import com.compraFacil.services.exceptions.DataIntegrityException;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalizacaoServiceTest {

	@Autowired
    private LocalizacaoService service;

	@Transactional
    @Test
    public void testFindAll() {
        List<Localizacao> list = service.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 4, list.size());
    }
    
	@Transactional
    @Test
    public void testFindOne() {
        Integer id = 1;
        Localizacao loc = service.find(id);
        
        Assert.assertNotNull("failure - expected not null", loc);
        Assert.assertEquals("failure - expected id attribute match", id, loc.getId());
    }
    
	@Transactional
    @Test(expected=ObjectNotFoundException.class) 
    public void testFindOneNotFound() {
    	Integer id = Integer.MAX_VALUE;
    	
        service.find(id);
    }
    
    @Transactional
    @Test
    public void testInsert() {
        Localizacao loc = new Localizacao(null, "-486418648", "-68456846484", "uberlandia shopping test");
        Localizacao createdLoc = service.insert(loc);
        
        Assert.assertNotNull("failure - expected not null", loc);
        Assert.assertNotNull("failure - expected id attribute not null", createdLoc.getId());
        Assert.assertEquals("failure - expected longitude attribute to match", loc.getLongitude(), createdLoc.getLongitude()); 
        Assert.assertEquals("failure - expected latitude attribute to match", loc.getLatitude(), createdLoc.getLatitude()); 
        Assert.assertEquals("failure - expected descricao attribute to match", loc.getDescricao(), createdLoc.getDescricao()); 
        
        List<Localizacao> list = service.findAll();
        Assert.assertEquals("failure - expected size", 5, list.size());
    }
    
    @Transactional
    @Test
    public void testCreateWithId() {
    	Integer id = Integer.MAX_VALUE;
    	Localizacao loc = new Localizacao(null, "-48648648", "-68456846484", "uberlandia shopping test");

        service.insert(loc);

        Assert.assertNotNull("failure - expected not null", loc);
        Assert.assertNotEquals("failure - expected id attribute to not match", loc.getId() , id);
    }
    
    @Transactional
    @Test
    public void testUpdate() {
    	Integer id = 2;
        Localizacao loc = service.find(id);

        Assert.assertNotNull("failure - expected not null", loc);

        Localizacao newLoc = new Localizacao(loc.getId(), loc.getLatitude() , loc.getLongitude(), loc.getDescricao());
        newLoc.setDescricao("teste");
        
        Localizacao updatedLoc = service.update(newLoc, loc);

        Assert.assertNotNull("failure - expected not null", updatedLoc);
        Assert.assertEquals("failure - expected id attribute to match", id, updatedLoc.getId());
        Assert.assertEquals("failure - expected descricao attribute to match", "teste", updatedLoc.getDescricao());
    }

    @Transactional
    @Test(expected=ObjectNotFoundException.class) 
    public void testDelete() {
        Integer id = 4;
        
        service.delete(id);
        service.find(id);
    }
    
    @Rollback(false)
    @Test(expected=DataIntegrityException.class) 
    public void testDeleteLocalizacaoWithAnuncio() {
        Integer id = 1;
        service.delete(id);
    }
}