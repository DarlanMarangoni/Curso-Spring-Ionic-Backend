package com.dmarangoni.cursomc.domain;

import javax.persistence.Entity;

import com.dmarangoni.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private int numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}
	
	public PagamentoComCartao(EstadoPagamento estado, Pedido pedido, int numeroDeParcelas) {
		super(estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
		
	}

	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(int numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
