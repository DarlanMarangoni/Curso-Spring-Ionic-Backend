package com.dmarangoni.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dmarangoni.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String cidade;
	
	public CidadeDTO() {
		
	}
	
	public CidadeDTO(Cidade obj) {
		this.id = obj.getId();
		this.cidade = obj.getNome();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return cidade;
	}

	public void setEstado(String estado) {
		this.cidade = cidade;
	}	
}
