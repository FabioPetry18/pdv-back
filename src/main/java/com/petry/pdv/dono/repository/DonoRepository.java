package com.petry.pdv.dono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.dono.entity.Proprietario;

@Repository
public interface DonoRepository extends JpaRepository<Proprietario, Long> {
	
	@Query(value = "SELECT COUNT(*) > 0 FROM PDV.DONO do INNER JOIN PDV.ASSINATURA assi ON assi.iddono = do.iddono where do.iddono = ?1", nativeQuery = true)
	Integer existsLojasAssociatesDono(Long donoId);
	
	
	
}
