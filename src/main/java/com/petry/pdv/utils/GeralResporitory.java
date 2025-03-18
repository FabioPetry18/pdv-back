package com.petry.pdv.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petry.pdv.adicional.dto.AdicionalAllDTO;
import com.petry.pdv.loja.service.LojaService;
import com.petry.pdv.pedido.dto.ProdutoResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class GeralResporitory {
	@PersistenceContext
	private EntityManager em;
	private Query query;
	
	
	@Autowired
	private LojaService lojaService;
	
	public List<AdicionalAllDTO> findAdicionalOrderByProductId(Long produtoId, Long lojaId, String name) {
	    StringBuilder sb = new StringBuilder();

	    sb.append("SELECT a.id, a.status, a.descricao, a.tag, a.titulo, a.valor, a.loja_id, pa.id as produtoid ");
	    sb.append("FROM pdv.adicional a ");
	    sb.append("LEFT JOIN pdv.produto_adicional pa ON a.id = pa.adicional_id AND pa.id = :produtoId ");
	    sb.append("WHERE 1=1 ");
	    sb.append("AND a.loja_id = :lojaId ");

	    if (name != null && !name.isEmpty()) {
	        sb.append("AND (LOWER(a.titulo) LIKE LOWER(CONCAT('%', :name, '%')) ");
	        sb.append("OR LOWER(a.tag) LIKE LOWER(CONCAT('%', :name, '%'))) ");
	    }

	    sb.append("ORDER BY CASE WHEN pa.id IS NOT NULL THEN 0 ELSE 1 END, a.tag");

	    
		query = this.em.createNativeQuery(sb.toString());
		query.setParameter("produtoId", produtoId);
		query.setParameter("lojaId", lojaId);
		if (name != null && !name.isEmpty()) {
	        query.setParameter("name", name);  
	    }

		List<Object[]> result = query.getResultList();
		return result.stream().map(obj -> 
        new AdicionalAllDTO(
            Long.valueOf(String.valueOf(obj[0])),     // id
            String.valueOf(obj[1]),                   // status
            String.valueOf(obj[2]),                   // descricao
            String.valueOf(obj[3]),                   // tag
            String.valueOf(obj[4]),                   // titulo
            (BigDecimal) obj[5],                      // valor
            lojaService.findLojaDTOById(Long.valueOf(String.valueOf(obj[6]))),
	            obj[7] != null ? Long.valueOf(String.valueOf(obj[7])) : null // produtoid (pode ser null)
	        )
	    ).toList();
	}
	

	
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
