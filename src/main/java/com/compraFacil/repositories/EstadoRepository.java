package com.compraFacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
		
}
