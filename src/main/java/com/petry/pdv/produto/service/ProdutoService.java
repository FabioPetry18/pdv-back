package com.petry.pdv.produto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.petry.pdv.adicional.dto.AdicionalDTO;
import com.petry.pdv.adicional.entity.Adicional;
import com.petry.pdv.loja.dto.LojaDTO;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.produto.dto.ProdutoDTO;
import com.petry.pdv.produto.dto.ProdutoPKDTO;
import com.petry.pdv.produto.dto.ProdutoRecord;
import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.produto.entity.ProdutoPK;
import com.petry.pdv.produto.repository.ProdutoRepository;
import com.petry.pdv.utils.PageResponse;
import com.petry.pdv.utils.s3.S3Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	S3Service s3Service;
	
	private final ModelMapper mapper = new ModelMapper();
	
	public ProdutoDTO buscarProdutoCodigoProduto(Long codigoProduto) throws Exception {
		Produto produto = repository.findById(codigoProduto).orElseThrow(() -> new Exception("Não encontrado"));
	    mapper.typeMap(Loja.class, LojaDTO.class).addMappings(mapper -> {
	        mapper.skip(LojaDTO::setConfiguracao); 
	        mapper.skip(LojaDTO::setHorarios); 
	    });

	    return mapper.map(produto, ProdutoDTO.class); // Usa o ModelMapper para converter
	}
	
	public List<Produto> getAll(Long idloja) {
		List<Produto> prod =  repository.findByIdloja(idloja);
		return prod;
	}

	public ProdutoDTO update(ProdutoDTO dto) throws Exception {
		Produto entity = repository.findById(dto.getId())
				.orElseThrow(() -> new Exception("Não encontrado"));
		entity.getAdicionais().clear(); //para permitir editar os adicionais
		mapper.typeMap(ProdutoPKDTO.class, ProdutoPK.class);
		mapper.typeMap(ProdutoDTO.class, Produto.class);	
		mapper.typeMap(AdicionalDTO.class, Adicional.class);	    

		mapper.map(dto,entity);
		repository.save(entity);
		return dto;
	}
	public ProdutoDTO save(ProdutoRecord produtoRecord) {
	  String profileImageUrl = s3Service.uploadFile(
			  	produtoRecord.productFile(),
                UUID.randomUUID().toString());
		
		produtoRecord.dto().setImagem(profileImageUrl);
		mapper.typeMap(ProdutoDTO.class, Produto.class);
		mapper.typeMap(AdicionalDTO.class, Adicional.class);
		Produto entity = mapper.map(produtoRecord.dto(), Produto.class);
		repository.save(entity);
		return produtoRecord.dto();
	}
	 public PageResponse<ProdutoDTO> buscarProdutosPaginados(int page, int size, Long idLoja) {
	        Pageable pageable = PageRequest.of(page - 1, size);
	        Page<Produto> produtos =  repository.findByLojaId(idLoja, pageable);
	        return new PageResponse<>(produtos.map(produto -> mapper.map(produto, ProdutoDTO.class)));
    }
}
