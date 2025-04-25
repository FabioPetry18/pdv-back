package com.petry.pdv.produto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.petry.pdv.produto.dto.ProdutoDTO;
import com.petry.pdv.produto.dto.ProdutoRecord;
import com.petry.pdv.produto.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
    private static final String DIRETORIO_UPLOAD = "uploads/";
    private final ModelMapper mapper = new ModelMapper();
	
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
	 
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProdutoDTO add(
	        @RequestPart("produto") String produtoJson,
	        @RequestPart(value = "productFile", required = false) MultipartFile productFile) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper()
			    .registerModule(new JavaTimeModule())  
			    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 ProdutoDTO dto = objectMapper.readValue(produtoJson, ProdutoDTO.class);

	    return produtoService.save(dto, productFile);
	}
	 
	 @PutMapping
	 public ResponseEntity putMethodName( @RequestBody ProdutoDTO dto) throws Exception {	 	
        return ResponseEntity.ok(produtoService.update(dto));

	 }
	 	
}
