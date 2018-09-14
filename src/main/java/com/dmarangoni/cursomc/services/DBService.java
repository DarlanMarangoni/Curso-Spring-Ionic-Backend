package com.dmarangoni.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.Categoria;
import com.dmarangoni.cursomc.domain.Cidade;
import com.dmarangoni.cursomc.domain.Cliente;
import com.dmarangoni.cursomc.domain.Endereco;
import com.dmarangoni.cursomc.domain.Estado;
import com.dmarangoni.cursomc.domain.ItemPedido;
import com.dmarangoni.cursomc.domain.Pagamento;
import com.dmarangoni.cursomc.domain.PagamentoComBoleto;
import com.dmarangoni.cursomc.domain.PagamentoComCartao;
import com.dmarangoni.cursomc.domain.Pedido;
import com.dmarangoni.cursomc.domain.Produto;
import com.dmarangoni.cursomc.domain.enums.EstadoPagamento;
import com.dmarangoni.cursomc.domain.enums.TipoCliente;
import com.dmarangoni.cursomc.repositories.CategoriaRepository;
import com.dmarangoni.cursomc.repositories.CidadeRepository;
import com.dmarangoni.cursomc.repositories.ClienteRepository;
import com.dmarangoni.cursomc.repositories.EnderecoRepository;
import com.dmarangoni.cursomc.repositories.EstadoRepository;
import com.dmarangoni.cursomc.repositories.ItemPedidoReposistory;
import com.dmarangoni.cursomc.repositories.PagamentoRepository;
import com.dmarangoni.cursomc.repositories.PedidoRepository;
import com.dmarangoni.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
		
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoReposistory itemPedidoReposistory;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public void instantiateTestDatabase () throws ParseException {
		Categoria cat1 = new Categoria ("Informática");
		Categoria cat2 = new Categoria ("Escritório");
		Categoria cat3 = new Categoria ("Cama mesa e banho");
		Categoria cat4 = new Categoria ("Eletrônicos");
		Categoria cat5 = new Categoria ("Jardinagem");
		Categoria cat6 = new Categoria ("Decoração");
		Categoria cat7 = new Categoria ("Perfumaria");
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		Produto p4 = new Produto("Mesa de escritório", 300.00);
		Produto p5 = new Produto("Toalha", 50.00);
		Produto p6 = new Produto("Colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("Roçadeira", 800.00);
		Produto p9 = new Produto("Abajour", 100.00);
		Produto p10 = new Produto("Pendente", 180.00);
		Produto p11 = new Produto("Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");
		
		Cidade c1 = new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente("Maria Silva", "marangoni.darlan1@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco("Rua Flores", "Apto 203", "300", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Cenetro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), e2, cli1);

		Pagamento pgto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoReposistory.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
