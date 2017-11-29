package com.compraFacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
		
}
