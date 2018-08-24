package com.dmarangoni.cursomc.domain.enums;

public enum EstadoPagamento {

	//tipos enumerados
	PENDENTE (1, "Pagamento Pendente"),
	QUITADO (2, "Pagamento Quitado"),
	CANCELADO (2, "Pagamento Cancelado");
	
	//variaveis
	private int cod;
	private String situacao;
	
	private EstadoPagamento(int cod, String situacao) {
		this.cod = cod;
		this.situacao = situacao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		System.out.println(cod);
		if(cod == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
