package com.petry.pdv.assinatura.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.repository.AssinaturaRepository;
import com.petry.pdv.proprietario.entity.Proprietario;
import com.petry.pdv.proprietario.repository.ProprietarioRepository;

@Service
public class AssinaturaService {

    @Autowired
    AssinaturaRepository repository;
    
    @Autowired
    ProprietarioRepository donoRepository;

    public Assinatura save(Assinatura entity) {
        return repository.save(entity);
    }
    public Assinatura getAssinaturaByDono(Proprietario dono) {
    	Assinatura assinatura = repository.getByDono(dono.getId());
    	return assinatura;
    }
}
