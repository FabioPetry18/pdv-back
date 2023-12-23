package com.petry.pdv.assinatura.controller;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
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

    @PostMapping
    public Assinatura save(@RequestBody Assinatura assinatura) {
        assinatura.setDataAbertura(new Date());
        return service.save(assinatura);
    }
}
