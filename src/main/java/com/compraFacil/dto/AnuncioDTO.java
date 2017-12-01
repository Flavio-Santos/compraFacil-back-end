package com.compraFacil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Propriedade;
import com.compraFacil.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AnuncioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	private String telefone;
	private CategoriaDTO categoria;
	private Localizacao localizacao;
	private Set<String> imagens = new HashSet<>();
	private List<Propriedade> propriedades = new ArrayList<>();
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataCriacao;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataFechamento;
	private Usuario vendedor;
	private Usuario comprador;
	
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
		this.setImagens(obj.getImagens());
		this.setPropriedades(obj.getPropriedades());
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

	public Set<String> getImagens() {
		return imagens;
	}

	public void setImagens(Set<String> imagens) {
		this.imagens = imagens;
	}

	public List<Propriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}
	
	

}