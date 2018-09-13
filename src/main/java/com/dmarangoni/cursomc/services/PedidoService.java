package com.dmarangoni.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.ItemPedido;
import com.dmarangoni.cursomc.domain.PagamentoComBoleto;
import com.dmarangoni.cursomc.domain.Pedido;
import com.dmarangoni.cursomc.domain.enums.EstadoPagamento;
import com.dmarangoni.cursomc.repositories.ItemPedidoReposistory;
import com.dmarangoni.cursomc.repositories.PagamentoRepository;
import com.dmarangoni.cursomc.repositories.PedidoRepository;
import com.dmarangoni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoReposistory itemPedidoReposistory;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(int id) {		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id +" Tipo: "+ Pedido.class.getName()));		
	}

	public Pedido insert(Pedido obj) {
		obj.setInstantDate(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstantDate());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoReposistory.saveAll((obj.getItens()));
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
}
