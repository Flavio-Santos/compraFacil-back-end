package com.compraFacil.test.services;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.compraFacil.domain.Usuario;
import com.compraFacil.services.UsuarioService;
import com.compraFacil.services.exceptions.ObjectNotFoundException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

	@Autowired
    private UsuarioService service;
	
    @Test
    public void testFindAll() {
        List<Usuario> list = service.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 3, list.size());
    }
    
    @Test
    public void testFindOne() {
        Integer id = 1;
        Usuario user = service.find(id);
        
        Assert.assertNotNull("failure - expected not null", user);
        Assert.assertEquals("failure - expected id attribute match", id, user.getId());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testFindOneNotFound() {
    	Integer id = Integer.MAX_VALUE;
    	
        service.find(id);
    }
    
    @Test
    public void testInsert() {
        Usuario user = new Usuario(null, "User test", "testUser@hotmail.com", null, "123");
        Usuario createdUser = service.insert(user);
        
        Assert.assertNotNull("failure - expected not null", user);
        Assert.assertNotNull("failure - expected id attribute not null", createdUser.getId());
        Assert.assertEquals("failure - expected nome attribute to match", user.getNome(), createdUser.getNome()); 
        Assert.assertEquals("failure - expected email attribute to match", user.getEmail(), createdUser.getEmail()); 
        
        List<Usuario> list = service.findAll();
        Assert.assertEquals("failure - expected size", 4, list.size());
    }

    @Test
    public void testCreateWithId() {
    	Integer id = Integer.MAX_VALUE;
    	Usuario user = new Usuario(id, "User test", "testUser@hotmail.com", null, "123");

        service.insert(user);

        Assert.assertNotNull("failure - expected not null", user);
        Assert.assertNotEquals("failure - expected id attribute to not match", user.getId() , id);
    }

    @Test
    public void testUpdate() {
    	Integer id = 2;
        Usuario user = service.find(id);

        Assert.assertNotNull("failure - expected not null", user);

        Usuario newUser = new Usuario(user.getId(), user.getNome(), user.getEmail(), user.getCpfOuCnpj(), user.getSenha());
        newUser.setNome("teste");
        
        Usuario updatedUser = service.update(newUser, user);

        Assert.assertNotNull("failure - expected not null", updatedUser);
        Assert.assertEquals("failure - expected id attribute to match", id, updatedUser.getId());
        Assert.assertEquals("failure - expected nome attribute to match", "teste", updatedUser.getNome());
    }
    
    @Test(expected=ObjectNotFoundException.class) 
    public void testDelete() {
        Integer id = 3;

        service.delete(id);
        service.find(id);
    }

}