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
import com.dmarangoni.cursomc.dto.CategoriaDTO;
import com.dmarangoni.cursomc.repositories.CategoriaRepository;
import com.dmarangoni.cursomc.services.exceptions.DataIntegrityException;
import com.dmarangoni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(int id) {		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id +" Tipo: "+ Categoria.class.getName()));		
	}

	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(int id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que tem produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage (int page, int linesPePage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPePage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getNome());
	}
}
