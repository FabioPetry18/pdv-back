package com.petry.pdv.produto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.produto.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

//	@GetMapping(path = "/{codproduto}")
//	public Produto getProdutoByCodProduto(@PathVariable String codproduto ) {
//		Produto produto = new Produto();
//		 produto = produtoService.buscarProdutoCodigoProduto(codproduto);
//		return produto;
//		
//	}
	@GetMapping(path = "/{idloja}")
	public List<Produto> getAllProducts(@PathVariable("idloja") Long idloja) {
		List<Produto> produto = new ArrayList<Produto>();
		 produto = produtoService.getAll(idloja);
		return produto;
		
	}
	
	@PostMapping
	public Produto insert(@RequestBody Produto produto){
		produto = produtoService.insert(produto);
		return produto;
		
	}
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto){
		ResponseEntity<Produto> produtoUpd = produtoService.update(produto);
		return produtoUpd;
		
	}
	@DeleteMapping
	public ResponseEntity<Produto> inativate(@RequestBody Produto produto){
		
		Produto find = produtoService.buscarProdutoCodigoProduto(produto.getId().toString());
		produto.setDesativated(!find.isDesativated());
		ResponseEntity<Produto> produtoUpd = produtoService.update(produto);
		return produtoUpd;
		
	}
	
	
}
