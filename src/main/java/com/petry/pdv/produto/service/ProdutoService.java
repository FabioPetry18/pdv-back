package com.petry.pdv.produto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;
	
	public Produto buscarProdutoCodigoProduto(String codigoProduto) {
		Produto prod =  repository.findByID(codigoProduto);
		return prod;
	}
	
	public List<Produto> getAll() {
		List<Produto> prod =  repository.findAll();
		return prod;
	}
	public Produto insert(Produto produto) {
		Produto prod =  repository.save(produto);
		return prod;
	}

	public ResponseEntity<Produto> update(Produto produto) {
	    Produto prod = repository.findByID(produto.getIdProduto().toString());
	    
	    if (ObjectUtils.isEmpty(prod)) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    Produto produtoUpd = repository.save(produto);
	    return ResponseEntity.ok(produtoUpd);
	}
	
}
