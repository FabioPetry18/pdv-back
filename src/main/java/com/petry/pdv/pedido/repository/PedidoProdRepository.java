package com.petry.pdv.pedido.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petry.pdv.pedido.dto.PesquisaPedidoResponse;
import com.petry.pdv.pedido.entity.PedidoProd;

@Repository
public interface PedidoProdRepository extends JpaRepository<PedidoProd, Long>{
	
	@Query(value = "SELECT * FROM pedidoprod WHERE idlojapedido = ?1 AND DATE(dtpedido) = CURDATE() AND status = 1 ORDER BY numpedido DESC",
	        countQuery = "SELECT COUNT(*) FROM pedido WHERE idloja = ?1 AND DATE(dtpedido) = CURDATE() AND status = 1",
	        nativeQuery = true)
	Page<PedidoProd> getByIdlojaWithPageable(Integer codloja, Pageable pageable);
	
	@Query(value = "select * from pedidoprod where idlojapedido = ?1  AND DATE(dtpedido) = CURDATE() AND status = ?2 ORDER BY numpedido DESC", nativeQuery = true)
	List<PedidoProd> getByIdloja(Integer codloja, Integer status);

	@Query(value = "SELECT  "
			+ "    prod.numpedido as numpedido, "
			+ "    produto.descricao as descricao, "
			+ "    produto.descricaocompl as descricaoCompl, "
			+ "    produto.codproduto as codproduto, "
			+ "    CONCAT(ende.cep,',',ende.bairro,',',ende.rua,', nº ',ende.numero) as endereco "
			+ "FROM "
			+ "    pedidoprod prod "
			+ "        INNER JOIN "
			+ "    produto produto ON prod.codproduto = produto.codproduto "
			+ "        AND prod.idlojapedido = produto.idloja "
			+ "        JOIN "
			+ "    endereco ende ON ende.idcliente = prod.idcliente "
			+ "        AND ende.id = prod.idendereco "
			+ "WHERE "
			+ "    prod.idlojapedido = ?1 "
			+ "        AND DATE(prod.dtpedido) = CURDATE() "
			+ "        AND prod.status = ?2 "
			+ "ORDER BY prod.numpedido DESC ", nativeQuery = true)

	List<PesquisaPedidoResponse> getByIdlojaAndNomeProduto(Integer codloja, Integer status);
	@Query(value = "SELECT IFNULL(MAX(numpedido), 0) AS ultimo_numpedido FROM pedido WHERE idlojapedido = ?1", nativeQuery = true)
	Long getUltimoIndexPedido(Long idLojaPedido);
}
