package com.petry.pdv.produto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.produto.entity.ProdutoPK;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	@Query(value = "SELECT * FROM pdv.produto WHERE id = :produto", nativeQuery = true)
	Produto findByID(@Param("produto") Long codigoProduto);
	
	Optional<Produto> findById(Long idProduto);     

	Page<Produto> findByLojaId(Long idLoja, Pageable pageable);     
	
	@Query(value = "SELECT * FROM PDV.PRODUTO WHERE idloja = :idloja", nativeQuery = true)
	List<Produto> findByIdloja(Long idloja);

	
	@Query(value = "SELECT IFNULL(MAX(codproduto), 0) AS ultimo_numproduto FROM produto WHERE idloja = ?1", nativeQuery = true)
	Long getUltimoIndexProduto(Long idloja);
	

}
