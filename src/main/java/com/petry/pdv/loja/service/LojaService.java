package com.petry.pdv.loja.service;

import java.util.List;

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
	private ProprietarioRepository donoRepository;
	
	private final ModelMapper mapper = new ModelMapper();
	
	public List<Loja> getAll(Long proprietarioid){
		return repository.findByProprietarioId(proprietarioid);
	}

	public Loja add(LojaDTO loja, Long proprietarioid) {	
		Loja loj = new Loja();
		if(donoRepository.existsById(proprietarioid)) {
			mapper.typeMap(LojaDTO.class, Loja.class);
			loj = mapper.map(loja, Loja.class);
			loj.setProprietario(new Proprietario());
			loj.getProprietario().setId(proprietarioid);
			loj.getConfiguracao().setId(null);
		}
		return repository.save(loj);
		
	}

	public boolean buscarPorId(Long id) {
		return repository.buscarPorId(id.toString()) == 1 ? true : false;
	}

	public Loja existsByIdLoja(Long id){
		return repository.existsByIdLoja(id);
	}


	public void delete(Loja loja) {
		 repository.delete(loja);
		
	}

	public Loja update(Loja loja) {
		return repository.save(loja);
		
	}
}
