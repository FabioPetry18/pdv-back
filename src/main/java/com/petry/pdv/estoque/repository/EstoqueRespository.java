package com.petry.pdv.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.estoque.entity.Estoque;

@Repository
public interface EstoqueRespository extends JpaRepository<Estoque, Long>{
	
	@Query(value = "select 1 from pdv.estoque est inner join pdv.produto prod ON prod.idprod = est.idprod inner join pdv.loja loj ON loj.idloja = est.idloja  where est.idestoque = :idest and loj.idloja = :idloj and prod.idproduto = :idprod ", nativeQuery = true)
	Integer findByPkAndLoja(@Param("idprod") String idProduto, @Param("idest") String idEstoque, @Param("idloj") String idLoja);

}
