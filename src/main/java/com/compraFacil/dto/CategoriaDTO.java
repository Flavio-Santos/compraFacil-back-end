package com.compraFacil.dto;

import com.compraFacil.domain.Categoria;

public class CategoriaDTO {

	private Integer id;
	private String nome;
	
	public CategoriaDTO(){
		
	}
	public CategoriaDTO(Categoria cat) {
		this.setId(cat.getId());
		this.setNome(cat.getNome());
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
}