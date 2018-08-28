package com.dmarangoni.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.Pedido;
import com.dmarangoni.cursomc.repositories.PedidoRepository;
import com.dmarangoni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(int id) {		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id +" Tipo: "+ Pedido.class.getName()));		
	}

}
