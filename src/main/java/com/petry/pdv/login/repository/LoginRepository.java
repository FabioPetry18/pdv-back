package com.petry.pdv.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.petry.pdv.login.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>{


	//Login findByUsuarioAndSenha(String usuario, String senha);
	
	UserDetails findByUsuario(String login);
	
	@Query(name = "select * from pdv.login where usuario = ?1", nativeQuery = true)
    Login findLoginByUsuario(String teste);}
