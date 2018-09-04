package com.dmarangoni.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmarangoni.cursomc.domain.Categoria;
import com.dmarangoni.cursomc.domain.Produto;
import com.dmarangoni.cursomc.dto.CategoriaDTO;
import com.dmarangoni.cursomc.dto.ProdutoDTO;
import com.dmarangoni.cursomc.resources.util.URL;
import com.dmarangoni.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable int id) {
		Produto obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam (value="nome", defaultValue="") String nome,
			@RequestParam (value="categorias", defaultValue="") String categorias,
			@RequestParam (value="page", defaultValue="0")int page, 
			@RequestParam (value="linesPerPage", defaultValue="24")int linesPePage, 
			@RequestParam (value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value="direction", defaultValue="ASC")String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPePage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
