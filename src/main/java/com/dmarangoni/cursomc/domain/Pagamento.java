package com.dmarangoni.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.dmarangoni.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)//Para mapear uma herança (tabelão ou tab individual)
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId //para mapear a coluna do Pedido com mesmo nome
	private Pedido pedido;
	
	public Pagamento() {
		
	}

	public Pagamento(EstadoPagamento estadoPagamento, Pedido pedido) {
		super();
		this.estado = (estadoPagamento == null) ? null : estadoPagamento.getCod();
		this.pedido = pedido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estado = estadoPagamento.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Pagamento other = (Pagamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
