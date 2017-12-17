package com.compraFacil.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;

public class CategoriaDTO {

	private Integer id;
	private String nome;
	private List<AnuncioDTO> anuncios = new ArrayList<>();
	
	public CategoriaDTO(){
		
	}
	public CategoriaDTO(Categoria cat) {
		this.setId(cat.getId());
		this.setNome(cat.getNome());
		this.anuncios = cat.getAnuncios().stream().map(obj -> new AnuncioDTO(obj)).collect(Collectors.toList());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<AnuncioDTO> getAnuncios() {
		return anuncios;
	}
	public void setAnuncios(List<AnuncioDTO> anuncios) {
		this.anuncios = anuncios;
	}
	
}