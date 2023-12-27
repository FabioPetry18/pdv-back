package com.petry.pdv.assinatura.service;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.repository.AssinaturaRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaService {

    @Autowired
    AssinaturaRepository repository;

    public Assinatura save(Assinatura assinatura) {
        assinatura.setDataAbertura(new Date());
        return repository.save(assinatura);
    }
}
