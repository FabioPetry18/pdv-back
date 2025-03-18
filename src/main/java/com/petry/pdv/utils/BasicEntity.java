package com.petry.pdv.utils;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BasicEntity extends BasicDateRegister {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "status", columnDefinition = "VARCHAR(10) DEFAULT 'Ativo' CHECK (status IN ('Ativo', 'Inativo'))", nullable = false)
    private String status = "Ativo";

}
