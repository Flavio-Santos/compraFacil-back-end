package com.compraFacil.dto;

import java.io.Serializable;
import java.util.Date;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Usuario;

public class AnuncioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	private String telefone;
	private Date dataCriacao;
	private Date dataFechamento;
	private Usuario vendedor;
	private Usuario comprador;
	private Localizacao localizacao;
	private CategoriaDTO categoria;

	public AnuncioDTO() {
	}

	public AnuncioDTO(Anuncio obj) {
		id = obj.getId();
		valor = obj.getValor();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		telefone = obj.getTelefone();
		dataCriacao = obj.getDataCriacao();
		dataFechamento = obj.getDataFechamento();
		vendedor = obj.getVendedor();
		comprador = obj.getComprador();
		localizacao = obj.getLocalizacao();
		this.categoria = new CategoriaDTO(obj.getCategoria());
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}


	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoriaDTO) {
		this.categoria = categoriaDTO;
	}
	
	

}