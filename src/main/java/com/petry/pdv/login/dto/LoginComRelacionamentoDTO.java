package com.petry.pdv.login.dto;

import com.petry.pdv.funcionario.dto.FuncionarioDTO;
import com.petry.pdv.login.UserTypes;
import com.petry.pdv.proprietario.dto.ProprietarioDTO;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoginComRelacionamentoDTO {
    private String usuario;
    private String senha;
    private String  primeiroacesso;
    private String acessos;
    private UserTypes userType ;
    private ProprietarioDTO proprietario;
    private FuncionarioDTO funcionario;
}


