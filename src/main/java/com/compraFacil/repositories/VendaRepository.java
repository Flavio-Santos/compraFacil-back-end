package com.compraFacil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{

	List<Venda> findByVendedor(Integer id);
	
}
