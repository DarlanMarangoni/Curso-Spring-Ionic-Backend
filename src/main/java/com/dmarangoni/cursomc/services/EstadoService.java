package com.dmarangoni.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.Categoria;
import com.dmarangoni.cursomc.domain.Estado;
import com.dmarangoni.cursomc.dto.CategoriaDTO;
import com.dmarangoni.cursomc.repositories.EstadoRepository;
import com.dmarangoni.cursomc.services.exceptions.DataIntegrityException;
import com.dmarangoni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> listar() {		
		List <Estado> listaEstado= repo.findAllByOrderByNome();
		return listaEstado;		
	}
}
