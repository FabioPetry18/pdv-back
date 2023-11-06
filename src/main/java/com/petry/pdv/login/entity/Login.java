package com.petry.pdv.login.entity;

import com.petry.pdv.cliente.entity.Cliente;
import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.login.UserTypes;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "login")
public class Login {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    
    @Column(name = "acessos")
    private String acessos;

    @Column(name = "userType")
    private UserTypes userType ;
    
    @Column(name = "idUser")
    private Long idUser; 
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    private Funcionario funcionario;
   

}