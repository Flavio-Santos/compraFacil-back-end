package com.compraFacil.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compraFacil.domain.Localizacao;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer>{
	
	@Query(nativeQuery=true, value = "SELECT * FROM localizacao WHERE longitude = ?1 and latitude = ?2 LIMIT 1")
    public Localizacao verifyLocation(String longitude, String latitude);

}
