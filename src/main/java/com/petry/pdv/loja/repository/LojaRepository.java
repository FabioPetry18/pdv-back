package com.petry.pdv.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.loja.entity.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>{
	
	@Query(value = "SELECT 1 FROM PDV.LOJA WHERE ID = :idloja", nativeQuery = true)
	Integer buscarPorId(@Param("idloja") String id);
		
}
