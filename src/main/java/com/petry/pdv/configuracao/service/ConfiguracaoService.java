package com.petry.pdv.configuracao.service;

import com.petry.pdv.configuracao.dto.ConfiguracaoDTO;
import com.petry.pdv.configuracao.entity.Configuracao;
import com.petry.pdv.configuracao.repository.ConfiguracaoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    @Autowired
    ConfiguracaoRepository repository;

	private final ModelMapper mapper = new ModelMapper();

    public Configuracao save(ConfiguracaoDTO configuracao, Long lojaId){
    	mapper.typeMap(ConfiguracaoDTO.class, Configuracao.class);
    	
        return repository.save(mapper.map(configuracao, Configuracao.class));
    }
}
