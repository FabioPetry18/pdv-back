package com.petry.pdv.adicional.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.adicional.entity.Adicional;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long>{
	
	@Query(value = """
		     SELECT a.id, a.status,a.descricao,a.tag,a.titulo,a.valor,a.loja_id, pa.id as produtoid
    FROM pdv.adicional a 
		    LEFT JOIN pdv.produto_adicional pa ON a.id = pa.adicional_id AND pa.id = :produtoId
            where 1=1
            and a.loja_id = :lojaId
            
		    ORDER BY CASE WHEN pa.id IS NOT NULL THEN 0 ELSE 1 END, a.titulo
		    """, nativeQuery = true)
		List<Object[]> findAdicionaisByProdutoId(@Param("produtoId") Long produtoId, @Param("lojaId") Long lojaId);
		
		Page<Adicional> findByLojaId(Long idLoja, Pageable pageable);     




}
