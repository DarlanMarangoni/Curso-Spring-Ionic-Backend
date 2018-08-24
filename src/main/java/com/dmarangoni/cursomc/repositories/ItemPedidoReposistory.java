package com.dmarangoni.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmarangoni.cursomc.domain.ItemPedido;

@Repository
public interface ItemPedidoReposistory extends JpaRepository<ItemPedido, Integer>{
	
}
