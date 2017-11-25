package com.compraFacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
		
}
