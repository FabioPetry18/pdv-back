package com.petry.pdv.loja.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.loja.dto.LojaDTO;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.repository.LojaRepository;
import com.petry.pdv.proprietario.entity.Proprietario;
import com.petry.pdv.proprietario.repository.ProprietarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LojaService {
	
	@Autowired
	private LojaRepository repository;
	
	@Autowired
	private ProprietarioRepository proprietarioRepository;
	
	private final ModelMapper mapper = new ModelMapper();
	
	public List<LojaDTO> getAll(Long proprietarioid) {
	    List<Loja> lojas = repository.findByProprietarioId(proprietarioid);
	    mapper.typeMap(Loja.class, LojaDTO.class);
	    
	    return lojas.stream()
	                .map(loja -> mapper.map(loja, LojaDTO.class))
	                .collect(Collectors.toList());
	}

	public LojaDTO add(LojaDTO dto, Long proprietarioid) throws Exception {	
		Loja loj = new Loja();
		Proprietario entity = proprietarioRepository.findById(proprietarioid).orElseThrow(() -> new Exception("Proprietario não cadastrado"));
			mapper.typeMap(LojaDTO.class, Loja.class);
			loj = mapper.map(dto, Loja.class);
			loj.setProprietario(entity);
			repository.save(loj);
			return dto;
	}

	public boolean buscarPorId(Long id) {
		return repository.buscarPorId(id.toString()) == 1 ? true : false;
	}

	public LojaDTO findLojaDTOById(Long id){
		mapper.typeMap(Loja.class, LojaDTO.class);
		Loja loja = repository.existsByIdLoja(id);
		return mapper.map(loja, LojaDTO.class);
	}


	public void delete(Loja loja) {
		 repository.delete(loja);
		
	}

	public Loja update(Loja loja) {
		return repository.save(loja);
		
	}
}
