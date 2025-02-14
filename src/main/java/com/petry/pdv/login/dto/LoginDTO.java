package com.petry.pdv.login.dto;

import com.petry.pdv.login.UserTypes;

import lombok.Data;


@Data
public class LoginDTO {
    private Long id;
    private String usuario;
    private String senha;
    private String  primeiroacesso;
    private String acessos;
    private UserTypes userType ;
    //private ProprietarioDTO proprietario;
    //private FuncionarioDTO funcionario;
}
