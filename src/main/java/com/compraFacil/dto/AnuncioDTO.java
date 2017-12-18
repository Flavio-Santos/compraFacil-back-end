package com.compraFacil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.compraFacil.domain.Anuncio;
import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Propriedade;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AnuncioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	private Boolean vendido;
	private String telefone;
	private Categoria categoria;
	private Localizacao localizacao;
	private Set<String> imagens = new HashSet<>();
	private List<Propriedade> propriedades = new ArrayList<>();
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataCriacao;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataFechamento;
	private UsuarioDTO vendedor;
	private UsuarioDTO comprador;
	
	public AnuncioDTO() {
	}

	public AnuncioDTO(Anuncio obj) {
		this.id = obj.getId();
		this.valor = obj.getValor();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
		this.setVendido(obj.getVendido());
		this.telefone = obj.getTelefone();
		this.dataCriacao = obj.getDataCriacao();
		this.dataFechamento = obj.getDataFechamento();
		this.vendedor = obj.getVendedor() == null ? null : new UsuarioDTO(obj.getVendedor());
		this.comprador = obj.getComprador() == null ? null : new UsuarioDTO(obj.getComprador());
		this.localizacao = obj.getLocalizacao();
		this.setCategoria(obj.getCategoria());
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

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
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

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
	}

	public UsuarioDTO getComprador() {
		return comprador;
	}

	public void setComprador(UsuarioDTO comprador) {
		this.comprador = comprador;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}