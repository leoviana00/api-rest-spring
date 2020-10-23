package com.pulse.osworks.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Arquivo que representa minha entidade "cliente"
// Mapeamento objeto relacional - Paralelo entre a classe e a tabela

//Notação do jakata persistence
@Entity 
public class Cliente {
	
	// Notação do jakata persistence que define a chave primária da entidade
	@Id
	
	// Definição de estratégia para geração da chave ID - Forma nativa utilizada pelo banco de dados, que no caso é o auto-increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// @Column (name = "nome") caso queira realizar um mapeamento de propriedades da entidade, nessa situação não precisa pq elas ja possuem o mesmo nome
	
	@NotBlank
	@Size (max = 60)
	private String nome;
	
	@NotBlank
	@Email //Valida o formato do email
	@Size (max = 255)
	private String email;
	
	@NotBlank
	@Size (max = 20)
	private String telefone;
	
	// Criação dos Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	// Criação dos HashCodes() and Equals() a partir do ID
	// Objetivo: Comparar objetos
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
