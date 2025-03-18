package com.petry.pdv.adicional.service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.adicional.dto.AdicionalAllDTO;
import com.petry.pdv.adicional.dto.AdicionalDTO;
import com.petry.pdv.adicional.entity.Adicional;
import com.petry.pdv.adicional.repository.AdicionalRepository;
import com.petry.pdv.loja.service.LojaService;
import com.petry.pdv.utils.GeralResporitory;

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
}
