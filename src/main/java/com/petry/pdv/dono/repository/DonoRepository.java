package com.petry.pdv.dono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.dono.entity.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long> {
	
	//@Query(name = "SELECT * FROM PDV.CLIENTE  WHERE USUARIO = :user AND SENHA = :senha ", nativeQuery = true)
	//Cliente findByUsuarioAndSenha(@Param("user") String user, @Param("senha") String senha);
	
	
}
