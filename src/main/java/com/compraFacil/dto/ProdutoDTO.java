package com.compraFacil.dto;

import java.io.Serializable;

import com.compraFacil.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valor;
	private String imagem;
	private String descricao;
	private CategoriaDTO categoria;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj, CategoriaDTO cat) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.valor = obj.getValor();
		this.imagem = obj.getImagem();
		this.descricao = obj.getDescricao();
		this.categoria = cat;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

}