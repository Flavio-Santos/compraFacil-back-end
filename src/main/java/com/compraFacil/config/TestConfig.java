package com.compraFacil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.compraFacil.services.TesteService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private TesteService tService;

	@Bean
	public boolean instantiateDatabase(){
		tService.instantiateTestDatabase();
		return true;
	}
}
