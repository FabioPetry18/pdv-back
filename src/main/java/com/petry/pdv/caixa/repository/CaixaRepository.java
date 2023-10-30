package com.petry.pdv.caixa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.caixa.entity.Caixa;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long>{
	
	@Query(value = "SELECT 1 FROM pdv.caixa CXA INNER JOIN pdv.funcionario FUNC ON CXA.IDCAIXA = FUNC.IDFUNCIONARIO where CXA.IDCAIXA = :idcaixa AND FUNC.IDFUNCIONARIO = :idfuncionario", nativeQuery = true)
	boolean getIdCxaAndIdFuncionario(@Param("idfuncionario") String idfuncionario,@Param("idcaixa") String idcaixa);

}
