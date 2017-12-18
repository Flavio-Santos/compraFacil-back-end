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

import com.compraFacil.domain.Categoria;
import com.compraFacil.services.CategoriaService;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoriaServiceTest {

	@Autowired
    private CategoriaService service;
    
    @Test
    public void testFindAll() {
        List<Categoria> list = service.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 3, list.size());
    }
    
    @Test
    public void testFindOne() {
        Integer id = 1;
        Categoria cat = service.find(id);
        
        Assert.assertNotNull("failure - expected not null", cat);
        Assert.assertEquals("failure - expected id attribute match", id, cat.getId());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testFindOneNotFound() {
    	Integer id = Integer.MAX_VALUE;
    	
        @SuppressWarnings("unused")
		Categoria cat = service.find(id);
    }
    
    @Test
    public void testInsert() {
        Categoria cat = new Categoria(null, "Insert Test");
        Categoria createCat = service.insert(cat);
        
        Assert.assertNotNull("failure - expected not null", cat);
        Assert.assertNotNull("failure - expected id attribute not null", createCat.getId());
        Assert.assertEquals("failure - expected nome attribute to match", cat.getNome(), createCat.getNome()); 
        
        List<Categoria> list = service.findAll();
        Assert.assertEquals("failure - expected size", 4, list.size());
    }

    @Test
    public void testCreateWithId() {
        Exception exception = null;

        Categoria cat = new Categoria();
        cat.setId(Integer.MAX_VALUE);
        cat.setNome("teste");

        try {
            service.insert(cat);
        } catch (EntityExistsException e) {
            exception = e;
        }

        Assert.assertNull("failure - expected null", exception);
    }

    @Test
    public void testUpdate() {
    	Integer id = 2;
        Categoria cat = service.find(id);

        Assert.assertNotNull("failure - expected not null", cat);

        Categoria newCat = new Categoria(cat.getId(), cat.getNome());
        newCat.setNome("teste");
        
        Categoria updatedCategoria = service.update(newCat, cat);

        Assert.assertNotNull("failure - expected not null", updatedCategoria);
        Assert.assertEquals("failure - expected id attribute to match", id, updatedCategoria.getId());
        Assert.assertEquals("failure - expected nome attribute to match", "teste", updatedCategoria.getNome());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testDelete() {
        Integer id = 2;

        service.delete(id);
        service.find(id);
    }

}