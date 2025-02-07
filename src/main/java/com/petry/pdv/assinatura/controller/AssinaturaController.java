package com.petry.pdv.assinatura.controller;

import com.petry.pdv.assinatura.dto.AssinaturaDTO;
import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.dono.entity.Proprietario;
import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.dono.service.DonoService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/assinatura")
public class AssinaturaController {

    @Autowired
    AssinaturaService service;
    
    @Autowired
    DonoRepository donoRepository;
    
    @PostMapping
    @Transactional
    public Assinatura save(@RequestBody AssinaturaDTO assinatura) {
    	Proprietario proprietario = new Proprietario().builder()
    			.nome(assinatura.getProprietario().getNome())
    			.sobrenome(assinatura.getProprietario().getSobrenome())
    			.telefone(assinatura.getProprietario().getTelefone())
    			.build();
    	
    	proprietario = donoRepository.save(proprietario);    	
       	
    	Assinatura entity = new Assinatura().builder()
    			.dataAbertura(new Date())
    			.dataFechamento(null)
    			.dataUltimoPagamento(null)
    			.qtdLojas(assinatura.getQtdLojas())
    			.proprietario(proprietario)
    			.build()
    			;
        return service.save(entity);
    }
}
