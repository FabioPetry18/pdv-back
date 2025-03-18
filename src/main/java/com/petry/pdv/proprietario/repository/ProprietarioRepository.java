package com.petry.pdv.proprietario.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.proprietario.entity.Proprietario;


@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
	
	@Query(value = "SELECT COUNT(*) > 0 FROM PDV.DONO do INNER JOIN PDV.ASSINATURA assi ON assi.iddono = do.iddono where do.iddono = ?1", nativeQuery = true)
	Integer existsLojasAssociatesDono(Long donoId);
	
	@Query("SELECT DISTINCT p FROM Proprietario p " +
		       "JOIN FETCH p.lojas l " +
		       "LEFT JOIN FETCH l.configuracao " +
		       "WHERE p.login = :login")
		Proprietario findByLogin(@Param("login") Login login);
	

	
	Page<Proprietario> findByTelefone(Long telefone,Pageable pageable);
	
}
