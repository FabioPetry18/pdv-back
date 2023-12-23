package com.petry.pdv.assinatura.service;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.repository.AssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {

    @Autowired
    AssinaturaRepository repository;

    public Assinatura save(Assinatura assinatura) {
        return repository.save(assinatura);
    }
}
