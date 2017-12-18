package com.compraFacil.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.compraFacil.domain.Usuario;
import com.compraFacil.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioDTO implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private Integer id;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
		private String nome;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Email(message="Email inválido")
		private String email;
		private String avatar;

		public UsuarioDTO() {
		}
		
		public UsuarioDTO(Usuario obj) {
			id = obj.getId();
			nome = obj.getNome();
			email = obj.getEmail();
			this.avatar = obj.getAvatar();	
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
	}
