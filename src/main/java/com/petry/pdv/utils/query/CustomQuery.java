package com.petry.pdv.utils.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.utils.Acessos;
import com.petry.pdv.utils.LoginResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomQuery {
	
	@PersistenceContext
	private EntityManager em;
	private Query query;
	
	
	
	public LoginResponse buscarInfosCliente(String username, String token) {
		String sql = "select login.usuario, login.user_type, login.id_user, login.acessos, dono.qtdlojas, loj.id, loj.nome, func.id, func.nome, func.idloja, login.primeiroacesso from pdv.login login left join pdv.dono dono ON login.id_user = dono.id  left join pdv.loja loj ON  login.id_user = loj.iddono left join funcionario func on func.idloja = loj.id where login.usuario = :user";
		List<Object[]> response  = this.em
				.createNativeQuery(sql)
				.setParameter("user", username)
				.getResultList();
		
		List<Loja> lojas = new ArrayList<>(); 
		List<Acessos> acessos = new ArrayList<>();
		LoginResponse login = new LoginResponse();
		
		if(String.valueOf(response.get(0)[3]) != null) { 
			List<String> acessosString = Arrays.asList(String.valueOf(response.get(0)[3]).split(",") );
				acessosString.forEach(acesso -> {
					if(acesso.equalsIgnoreCase("dashboard")) {
						acessos.add(new Acessos(acesso.substring(0,1).toUpperCase().concat(acesso.substring(1).trim()), "/"+acesso.trim()));	
					}else {
						acessos.add(new Acessos(acesso.substring(0,1).toUpperCase().concat(acesso.substring(1).trim()), "/dashboard/"+acesso.trim()));						
					}
				});				
		}
		
		response.forEach(p -> {
			Funcionario func = new Funcionario();
			Loja loja = new Loja();
			func.setId(String.valueOf(p[7]));
			func.setNome(String.valueOf(p[8]));
			func.setIdLoja(p[9] == null ? 0 : Long.valueOf(String.valueOf(p[9])));
			loja.setId(Long.valueOf(p[5].toString()));
			loja.setNome(String.valueOf(p[6]));
			loja.getFuncionarios().add(func);
			lojas.add(loja);
			System.out.println(String.valueOf(p[3]));
			String acess = String.valueOf(p[3]);
			
			
			login.setPrimeiroAcesso((boolean) p[10]);
			login.setUsername(String.valueOf(p[0]));
			login.setUserType(String.valueOf(p[1]));
			login.setId(String.valueOf(p[2]));
			//login.setAcessos(String.valueOf(response.get(0)[3]));
			login.setAcessos(acessos);
			login.setQtdLojas(Integer.valueOf(String.valueOf(p[4])));
			login.setLojas(lojas);
			login.setVisualizacaoLoja(lojas.get(0));
			login.setToken(token);
			
		});
		
			return login;
	
		 
		
	}
	
	public LoginResponse buscarInfosAdmin(String username, String token) {
		String sql = "select usuario, user_type, login.acessos "
					+ "from pdv.login  "
					+ "where usuario = :user";
		List<Object[]> response  = this.em
				.createNativeQuery(sql)
				.setParameter("user", username)
				.getResultList();
		
		List<Acessos> acessos = new ArrayList<>();
		LoginResponse login = new LoginResponse();
		
		acessos.add(new Acessos("Dono", "/dashboard/dono"));	

		
		response.forEach(p -> {
			
			login.setUsername(String.valueOf(p[0]));
			login.setUserType(String.valueOf(p[1]));
			login.setAcessos(acessos);
			login.setToken(token);
			
		});
		
		return login;
		
		
		
	}
	
//	public record LoginResponseDTO(String userId, List<Loja> lojas, Integer qtdLojas, boolean isAdmin, List<Acessos> acessos ,String token) {

}
