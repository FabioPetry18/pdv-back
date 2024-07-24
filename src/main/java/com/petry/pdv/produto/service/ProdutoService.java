package com.petry.pdv.produto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.produto.entity.ProdutoPK;
import com.petry.pdv.produto.entity.ProdutoRequest;
import com.petry.pdv.produto.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;
	
	public Produto buscarProdutoCodigoProduto(String codigoProduto) {
		Produto prod =  repository.findByID(codigoProduto);
		return prod;
	}
	
	public List<Produto> getAll(Long idloja) {
		List<Produto> prod =  repository.findByIdloja(idloja);
		return prod;
	}
	public Produto insert(ProdutoRequest produto) {
		Long codprod = repository.getUltimoIndexProduto(produto.getIdloja()) + 1;
		Produto prod = new Produto();
		ProdutoPK pk = new ProdutoPK();
		pk.setCodproduto(codprod);
		pk.setIdloja(produto.getIdloja());
		prod.setDesativated(false);
		prod.setDescricao(produto.getDescricao());
		prod.setDescricaoCompl(produto.getDescricaoCompl());
		prod.setEan(produto.getEan());
		prod.setId(pk);
		prod.setImagem(produto.getImagem());
		Produto prodSave =  repository.save(prod);
		return prodSave;
	}

	public ResponseEntity<Produto> update(Produto produto) {
	    Produto prod = repository.findByID(produto.getId().toString());
	    
	    if (ObjectUtils.isEmpty(prod)) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    Produto produtoUpd = repository.save(produto);
	    return ResponseEntity.ok(produtoUpd);
	}
	
}
