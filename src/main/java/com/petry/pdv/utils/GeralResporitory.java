package com.petry.pdv.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.petry.pdv.pedido.dto.ProdutoResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class GeralResporitory {
	@PersistenceContext
	private EntityManager em;
	private Query query;
	
	public List<ProdutoResponse> getByIdlojaAndNumeroPedido(Integer lojaPedido, Long numeroPedido){
		StringBuilder sb = new StringBuilder();			
		sb.append(" SELECT ");
		sb.append("		* ");
		sb.append(" FROM ");
		sb.append("	 pedidoprod prod ");
		sb.append("  INNER JOIN  ");
		sb.append("  produto produto ON prod.codproduto = produto.codproduto AND prod.idlojapedido = produto.idloja ");
		sb.append("  INNER JOIN ");
		sb.append("  endereco ende ON ende.idcliente = prod.idcliente AND ende.id = prod.idendereco ");
		sb.append("  INNER JOIN ");
		sb.append("  cliente cli on cli.idcliente = prod.idcliente ");
		sb.append(" WHERE  prod.idlojapedido = :lojaPedido ");
		sb.append("  AND DATE(prod.dtpedido) = CURDATE() ");
		sb.append("  AND prod.status = :status ");	
		sb.append(" ORDER BY ");
		sb.append("  prod.numpedido ASC  ");		

		
		query = this.em.createNativeQuery(sb.toString());
		query.setParameter("lojaPedido", lojaPedido);

		List<Object[]> result = query.getResultList();
		List<ProdutoResponse> list = new ArrayList<>();
		
		return result.stream().map(p -> {
			ProdutoResponse dto = new ProdutoResponse();
			dto.setDescricao(String.valueOf(p[1]));
		
			return dto;
		}).collect(Collectors.toList());
		
	}
}
