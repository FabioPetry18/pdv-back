package com.petry.pdv.proprietario.entity;

import java.util.List;
import java.util.Set;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.utils.BasicEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "pdv", name = "proprietario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario extends BasicEntity {
    
    @Column(name = "nome")
	private String nome;   
    
    @Column(name = "sobrenome")
   	private String sobrenome;    
    
    @Column(name = "telefone")
    private Long telefone;  
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assinatura_id", nullable = false, unique = true)
    private Assinatura assinatura;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false, unique = true)
    private Login login;
    
    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Loja> lojas;
}