package com.compraFacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Propriedade;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Integer>{
	
}
