package com.dmarangoni.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dmarangoni.cursomc.domain.Categoria;
import com.dmarangoni.cursomc.domain.Estado;

public class EstadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String estado;
	
	public EstadoDTO() {
		
	}
	
	public EstadoDTO(Estado obj) {
		this.id = obj.getId();
		this.estado = obj.getNome();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	
}
