package com.compraFacil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{

	@Query(nativeQuery=true, value = "SELECT * FROM anuncio WHERE Vendido = 0")
    List<Anuncio> FindAllNotSold();
}
