package com.petry.pdv.utils;

import com.petry.pdv.loja.entity.Loja;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class LojaBasicEntity extends BasicEntity {
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "loja_id") 
    private Loja loja;
}
