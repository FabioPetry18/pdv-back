package com.petry.pdv.configuracao.controller;

import com.petry.pdv.configuracao.entity.Configuracao;
import com.petry.pdv.configuracao.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/configuracao")
public class ConfiguracaoController {

    @Autowired
    ConfiguracaoService service;

    @PostMapping
    public Configuracao save(@RequestBody Configuracao configuracao){
        return service.save(configuracao);
    }
}
