package com.dmarangoni.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dmarangoni.cursomc.domain.Cidade;
import com.dmarangoni.cursomc.domain.Estado;
import com.dmarangoni.cursomc.dto.CidadeDTO;
import com.dmarangoni.cursomc.dto.EstadoDTO;
import com.dmarangoni.cursomc.services.CidadeService;
import com.dmarangoni.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> listar() {
		List<Estado> list= service.listar();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);		
	}	
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable int estadoId) {
		List<Cidade> list= cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(listDto);		
	}	
}


