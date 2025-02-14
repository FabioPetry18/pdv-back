package com.petry.pdv.assinatura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.proprietario.repository.ProprietarioRepository;


@RestController
@RequestMapping("/assinatura")
public class AssinaturaController {

    @Autowired
    AssinaturaService service;
    
    @Autowired
    ProprietarioRepository donoRepository;
    
//    @PostMapping
//    @Transactional
//    public Assinatura save(@RequestBody AssinaturaDTO assinatura) {
//    	
//    	Proprietario proprietario = new Proprietario().builder()
//    			.nome(assinatura.getProprietario().getNome())
//    			.sobrenome(assinatura.getProprietario().getSobrenome())
//    			.telefone(assinatura.getProprietario().getTelefone())
//    			.build();
//    	
//    	proprietario = donoRepository.save(proprietario);    	
//       	
//    	Assinatura entity = new Assinatura().builder()
//    			.dataAbertura(new Date())
//    			.dataFechamento(null)
//    			.dataUltimoPagamento(null)
//    			.qtdLojas(assinatura.getQtdLojas())
//    			//.proprietario(proprietario)
//    			.build()
//    			;
//        return service.save(entity);
//    }
}
