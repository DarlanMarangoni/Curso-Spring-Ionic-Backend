package com.dmarangoni.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.Cidade;
import com.dmarangoni.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado (int estadoId){
		return repo.findCidades(estadoId);
	}
}
