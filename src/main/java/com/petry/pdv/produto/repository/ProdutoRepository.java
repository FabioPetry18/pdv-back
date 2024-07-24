package com.petry.pdv.produto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.produto.entity.Produto;
import java.util.List;




@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	@Query(value = "SELECT * FROM PDV.PRODUTO WHERE codproduto = :produto", nativeQuery = true)
	Produto findByID(@Param("produto") String produto);
	
	@Query(value = "SELECT * FROM PDV.PRODUTO WHERE idloja = :idloja", nativeQuery = true)
	List<Produto> findByIdloja(Long idloja);
	
	@Query(value = "SELECT IFNULL(MAX(codproduto), 0) AS ultimo_numproduto FROM produto WHERE idloja = ?1", nativeQuery = true)
	Long getUltimoIndexProduto(Long idloja);
}
