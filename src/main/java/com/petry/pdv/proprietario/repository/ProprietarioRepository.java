package com.petry.pdv.proprietario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.proprietario.entity.Proprietario;


@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
	
	@Query(value = "SELECT COUNT(*) > 0 FROM PDV.DONO do INNER JOIN PDV.ASSINATURA assi ON assi.iddono = do.iddono where do.iddono = ?1", nativeQuery = true)
	Integer existsLojasAssociatesDono(Long donoId);
	

	Proprietario findByLogin(Login login);
	
}
