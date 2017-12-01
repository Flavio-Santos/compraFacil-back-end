package com.compraFacil.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.compraFacil.domain.Categoria;
import com.compraFacil.domain.Localizacao;
import com.compraFacil.domain.Usuario;


public class AnuncioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Double valor;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String descricao;
	private String telefone;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Date dataCriacao;
	private Date dataFechamento;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Usuario vendedor;
	private Usuario comprador;
	private Localizacao localizacao;
	
	private Categoria categoria;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String imagem1;
	private String imagem2;
	private String imagem3;

	public AnuncioNewDTO() {
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
	
	public String getImagem1() {
		return imagem1;
	}

	public void setImagem1(String imagem1) {
		this.imagem1 = imagem1;
	}

	public String getImagem2() {
		return imagem2;
	}

	public void setImagem2(String imagem2) {
		this.imagem2 = imagem2;
	}

	public String getImagem3() {
		return imagem3;
	}

	public void setImagem3(String imagem3) {
		this.imagem3 = imagem3;
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
		AnuncioNewDTO other = (AnuncioNewDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}