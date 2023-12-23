package com.petry.pdv.configuracao.service;

import com.petry.pdv.configuracao.entity.Configuracao;
import com.petry.pdv.configuracao.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    @Autowired
    ConfiguracaoRepository repository;

    public Configuracao save(Configuracao configuracao){
        return repository.save(configuracao);
    }
}
