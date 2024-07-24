package com.petry.pdv.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.petry.pdv.pedido.dto.PesquisaPedidoResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class GeralResporitory {
	@PersistenceContext
	private EntityManager em;
	private Query query;
	
	public List<PesquisaPedidoResponse> getByIdlojaAndNomeProduto(Integer lojaPedido, Integer status){
		StringBuilder sb = new StringBuilder();			
		sb.append(" SELECT ");
		sb.append("		 prod.numpedido, ");
		sb.append("		produto.descricao, ");
		sb.append("		prod.qtd_produto, ");
		sb.append("		produto.codproduto, ");
		sb.append("		CONCAT(ende.cep,',',ende.bairro,',',ende.rua,', nº ',ende.numero), ");
		sb.append("		cli.nomcli,	 ");
		sb.append("		prod.dtpedido	 ");
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
		query.setParameter("status", status);

		List<Object[]> result = query.getResultList();
		List<PesquisaPedidoResponse> list = new ArrayList<>();
		
		return result.stream().map(p -> {
			PesquisaPedidoResponse dto = new PesquisaPedidoResponse();
			dto.setNumpedido(Long.valueOf(String.valueOf(p[0])));
			dto.setDescricao(String.valueOf(p[1]));
			dto.setQtd(BigDecimal.valueOf(Double.valueOf(String.valueOf(p[2]))));
			dto.setCodproduto(Long.valueOf(String.valueOf(p[3])));
			dto.setEndereco(String.valueOf(p[4]));
			dto.setNomCli(String.valueOf(p[5]));
			dto.setDtPedido(String.valueOf(p[6]));
			return dto;
		}).collect(Collectors.toList());
		
	}
}
