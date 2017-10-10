package com.compraFacil.domain;

public class Produto {

	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	
	public Produto() {
		
	}
	public Produto(Integer id, Double valor, String nome, String descricao) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
