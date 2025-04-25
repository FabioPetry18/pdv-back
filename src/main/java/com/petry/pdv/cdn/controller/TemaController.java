package com.petry.pdv.cdn.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.cdn.dto.TemaDTO;
import com.petry.pdv.cdn.service.TemaService;
import com.petry.pdv.utils.s3.S3Service;

@RestController
@RequestMapping("/temas")
public class TemaController {
	@Autowired
	private S3Service s3Service;

    @PostMapping("/{clienteId}")
    public ResponseEntity<?> gerarTema(@PathVariable String clienteId, @RequestBody TemaDTO tema) {
        try {
            String css = TemaService.gerarTemaCSSStatic(tema);
            String nomeArquivo = clienteId + ".css";
            //TemaService.salvarTemaNoDisco(nomeArquivo, css);
            String temaUrl = s3Service.uploadCss(css, nomeArquivo);
            return ResponseEntity.ok("Tema gerado com sucesso: " + temaUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
}