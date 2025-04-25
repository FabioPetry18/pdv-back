package com.petry.pdv.adicional.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.petry.pdv.adicional.dto.AdicionalAllDTO;
import com.petry.pdv.adicional.dto.AdicionalDTO;
import com.petry.pdv.adicional.entity.Adicional;
import com.petry.pdv.adicional.repository.AdicionalRepository;
import com.petry.pdv.loja.dto.LojaDTO;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.service.LojaService;
import com.petry.pdv.produto.dto.ProdutoDTO;
import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.utils.GeralResporitory;
import com.petry.pdv.utils.PageResponse;

@Service
public class AdicionalService {
	@Autowired
	private AdicionalRepository repository;
    
    @Autowired
    private GeralResporitory geralRepository;
    
    @Autowired
    private LojaService lojaService;
    
    private final ModelMapper mapper = new ModelMapper();
    

    public List<AdicionalAllDTO> listarAdicionais(Long idloja, Long idproduto, String param) {
    	return geralRepository.findAdicionalOrderByProductId(idproduto, idloja, param);  	
    }
    public AdicionalDTO save(Long idloja,AdicionalDTO dto) {
    	mapper.typeMap(AdicionalDTO.class, Adicional.class);
    	Adicional entity = mapper.map(dto, Adicional.class);
    	repository.save(entity);  
    	return dto;	
    }
	 public PageResponse<AdicionalDTO> buscarAdicionaisPaginados(int page, int size, Long idLoja) {
		    final ModelMapper maper = new ModelMapper();

	        Pageable pageable = PageRequest.of(page - 1, size);
	        Page<Adicional> adicionais =  repository.findByLojaId(idLoja, pageable);
	        PropertyMap<Adicional, AdicionalDTO> adicionalMap = new PropertyMap<Adicional, AdicionalDTO>() {
	            @Override
	            protected void configure() {
	                skip(destination.getLoja());
	              
	            }
	        };

	        maper.addMappings(adicionalMap);
	        return new PageResponse<>(adicionais.map(produto -> maper.map(produto, AdicionalDTO.class)));

 }
}
