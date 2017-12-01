package com.compraFacil.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.compraFacil.dto.CategoriaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Anuncio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	private String telefone;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="localizacao_id")
	private Localizacao localizacao;
	
	@ElementCollection
	@CollectionTable(name="IMAGEM" )
	private Set<String> imagens = new HashSet<>();
	
	@OneToMany(mappedBy="anuncio")
	private List<Propriedade> propriedades = new ArrayList<>();
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataCriacao;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataFechamento;

	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Usuario vendedor;
	
	@ManyToOne
	@JoinColumn(name="comprador_id")
	private Usuario comprador;
	
	public Anuncio() {
	}

	public Anuncio(Integer id, Double valor, String nome, String descricao, String telefone, Date dataCriacao, Date dataFechamento, 
			Usuario comprador, Usuario vendedor, Localizacao localizacao, Categoria categoria) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.descricao = descricao;
		this.telefone = telefone;
		this.dataCriacao = dataCriacao;
		this.dataFechamento = dataFechamento;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.localizacao = localizacao;
		this.categoria = categoria;
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

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Propriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anuncio other = (Anuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}