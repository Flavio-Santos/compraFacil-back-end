package com.compraFacil.dto;


import java.io.Serializable;

import com.compraFacil.domain.Produto;


public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valor;
	private String descricao;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		valor = obj.getValor();
		descricao = obj.getDescricao();
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

	public Double getValor() {
		return valor;
	}

	public void getValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}