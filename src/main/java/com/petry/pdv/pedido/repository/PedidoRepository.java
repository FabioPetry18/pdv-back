package com.petry.pdv.pedido.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petry.pdv.pedido.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query(value = "SELECT * FROM pedido WHERE idlojapedido = ?1 AND DATE(dtpedido) = CURDATE() AND status = 1 ORDER BY numpedido DESC",
	        countQuery = "SELECT COUNT(*) FROM pedido WHERE idloja = ?1 AND DATE(dtpedido) = CURDATE() AND status = 1",
	        nativeQuery = true)
	Page<Pedido> getByIdlojaWithPageable(Integer codloja, Pageable pageable);
	
	@Query(value = "SELECT * FROM pedido WHERE idlojapedido = ?1 AND DATE(dtpedido) = CURDATE() AND status = ?2 ORDER BY numpedido DESC", nativeQuery = true)
	List<Pedido> getByIdloja(Integer codloja, Integer status);

	@Query(value = "SELECT IFNULL(MAX(numpedido), 0) AS ultimo_numpedido FROM pedido WHERE idlojapedido = ?1", nativeQuery = true)
	Long getUltimoIndexPedido(Long idLojaPedido);
}
