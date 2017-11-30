package com.compraFacil.dto;

<<<<<<< HEAD

=======
>>>>>>> buscaPorUsuario
import java.io.Serializable;

import com.compraFacil.domain.Produto;

<<<<<<< HEAD

=======
>>>>>>> buscaPorUsuario
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valor;
<<<<<<< HEAD
	private String descricao;
	private String imagem;
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	private CategoriaDTO categoria;
	
=======
	private String imagem;
	private String descricao;
	private CategoriaDTO categoria;

>>>>>>> buscaPorUsuario
	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj, CategoriaDTO cat) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.valor = obj.getValor();
<<<<<<< HEAD
		this.descricao = obj.getDescricao();
		this.categoria = cat;
		this.imagem = obj.getImagem();
=======
		this.imagem = obj.getImagem();
		this.descricao = obj.getDescricao();
		this.categoria = cat;
>>>>>>> buscaPorUsuario
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

<<<<<<< HEAD
=======
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

>>>>>>> buscaPorUsuario
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

<<<<<<< HEAD


=======
>>>>>>> buscaPorUsuario
}