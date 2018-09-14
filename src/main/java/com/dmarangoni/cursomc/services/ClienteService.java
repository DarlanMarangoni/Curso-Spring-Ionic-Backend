package com.dmarangoni.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dmarangoni.cursomc.domain.Cidade;
import com.dmarangoni.cursomc.domain.Cliente;
import com.dmarangoni.cursomc.domain.Endereco;
import com.dmarangoni.cursomc.domain.enums.TipoCliente;
import com.dmarangoni.cursomc.dto.ClienteDTO;
import com.dmarangoni.cursomc.dto.ClienteNewDTO;
import com.dmarangoni.cursomc.repositories.CidadeRepository;
import com.dmarangoni.cursomc.repositories.ClienteRepository;
import com.dmarangoni.cursomc.repositories.EnderecoRepository;
import com.dmarangoni.cursomc.services.exceptions.DataIntegrityException;
import com.dmarangoni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(int id) {		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id +" Tipo: "+ Cliente.class.getName()));		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData (newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(int id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há pedidos relacionadas");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage (int page, int linesPePage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPePage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {		
		return new Cliente (objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade ();
		cid.setId(objDto.getCidadeId());
		System.out.println(cid.getId() + cid.getNome());
		Endereco end = new Endereco(objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), 
									objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
