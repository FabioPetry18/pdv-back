package com.petry.pdv.produto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petry.pdv.produto.dto.ProdutoDTO;
import com.petry.pdv.produto.dto.ProdutoRecord;
import com.petry.pdv.produto.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
    private static final String DIRETORIO_UPLOAD = "uploads/";

	
	 @PostMapping("/upload")
	    public ResponseEntity<String> uploadImagem(@RequestParam("file") MultipartFile file) {
	        try {
	            // Criar diretório se não existir
	            String uploadDir = new ClassPathResource("static/uploads/").getFile().getAbsolutePath();

	            // Definir caminho do arquivo
	            // Cria um novo arquivo no diretório
	            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
	            Files.write(filePath, file.getBytes());

	            return ResponseEntity.ok("Arquivo salvo em: " + filePath.toString());

	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagem");
	        }
	    }
	 
 	@GetMapping("/paginator/{idLoja}")
    public ResponseEntity getProdutosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long idLoja) {

        return ResponseEntity.ok(produtoService.buscarProdutosPaginados(page, size, idLoja));
    }
 	
	@GetMapping("/{idproduto}")
    public ResponseEntity getProdutobyId (
            @PathVariable Long idproduto) throws Exception {
        return ResponseEntity.ok(produtoService.buscarProdutoCodigoProduto(idproduto));
    }
	 
     @PostMapping(consumes = "multipart/form-data")
	 public ProdutoDTO add(@ModelAttribute ProdutoRecord produtoRecord) {
		return produtoService.save(produtoRecord);
		 
	 }
	 
	 @PutMapping
	 public ResponseEntity putMethodName( @RequestBody ProdutoDTO dto) throws Exception {	 	
        return ResponseEntity.ok(produtoService.update(dto));

	 }
	 	
}
