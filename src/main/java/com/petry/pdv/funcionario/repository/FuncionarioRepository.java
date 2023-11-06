package com.petry.pdv.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.funcionario.entity.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
//	@Query(value = "SELECT 1 FROM PDV.FUNCIONARIO WHERE IDFUNCIONARIO = :idfunc", nativeQuery = true)
//	Integer getfuncionarioById(@Param("idfunc")String id);
	
//	@Query(name = "select 1 from pdv.funcionario func where func.usuario = :user and func.senha = :senha ", nativeQuery = true)
//	Integer autenticarFuncionario(@Param("user") String user, @Param("senha") String senha);
}
