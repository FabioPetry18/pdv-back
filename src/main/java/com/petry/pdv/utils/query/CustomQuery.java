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
		String sql = "select login.usuario, login.user_type, login.iduser, login.acessos, assi.qtd_lojas, loj.id, loj.nome,  login.primeiroacesso, dono.nome, dono.sobrenome from pdv.login login left join pdv.dono dono ON login.iduser = dono.iddono  left join pdv.loja loj ON  login.iduser = loj.iddono left join pdv.assinatura assi ON assi.iddono = dono.iddono where login.usuario = :user";
		List<Object[]> response  = this.em
				.createNativeQuery(sql)
				.setParameter("user", username)
				.getResultList();
		
		List<Loja> lojas = new ArrayList<>(); 
		LoginResponse login = new LoginResponse();
		
		response.forEach(p -> {
			Funcionario func = new Funcionario();
			Loja loja = new Loja();
			login.setUsername(String.valueOf(p[0]));
			login.setUserType(String.valueOf(p[1]));
			login.setId(String.valueOf(p[2]));
			login.setQtdLojas(Integer.valueOf(String.valueOf(p[4])));
			login.setNome(String.valueOf(p[8]));
			login.setSobrenome(String.valueOf(p[9]));
			login.setQtdLojas(Integer.valueOf(String.valueOf(p[4])));
			login.setToken(token);
			login.setPrimeiroAcesso(String.valueOf(p[7]));
			
		});
		
			return login;
	
		 
		
	}
	
	public LoginResponse buscarInfosAdmin(Login user, String token) {
		String sql = "select usuario, user_type, login.acessos "
					+ "from pdv.login  "
					+ "where usuario = :user";
		List<Object[]> response  = this.em
				.createNativeQuery(sql)
				.setParameter("user", user.getUsuario())
				.getResultList();
		
		List<Acessos> acessos = new ArrayList<>();
		LoginResponse login = new LoginResponse();
		
		acessos.add(new Acessos("Dono", "/dashboard/dono"));	

		
		response.forEach(p -> {
			login.setId(user.getIdUser());
			login.setUsername(String.valueOf(p[0]));
			login.setUserType(String.valueOf(p[1]));
			login.setAcessos(acessos);
			login.setToken(token);
			
		});
		
		return login;
		
		
		
	}
	
//	public record LoginResponseDTO(String userId, List<Loja> lojas, Integer qtdLojas, boolean isAdmin, List<Acessos> acessos ,String token) {

}
