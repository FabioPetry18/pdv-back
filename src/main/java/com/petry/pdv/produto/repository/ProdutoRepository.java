package com.petry.pdv.produto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.produto.entity.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	@Query(value = "SELECT * FROM PDV.PRODUTO WHERE ID = :produto", nativeQuery = true)
	Produto findByID(@Param("produto") String produto);
}
