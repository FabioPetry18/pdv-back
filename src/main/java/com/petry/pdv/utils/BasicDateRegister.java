package com.petry.pdv.utils;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasicDateRegister {
	
	@LastModifiedDate
    @Column(nullable = false ,columnDefinition = "timestamp")
    private LocalDateTime dataAtualizacao;
    
    @CreatedDate
    @Column(nullable = false ,columnDefinition = "timestamp", updatable = false)
    private LocalDateTime datacriacao;

}
