package com.petry.pdv.assinatura.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petry.pdv.assinatura.entity.Assinatura;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
	
	@Query(value = "SELECT * FROM PDV.ASSINATURA ASSI INNER JOIN PDV.DONO DON ON DON.IDDONO = ASSI.IDDONO where don.iddono = :donoId", nativeQuery =  true)
	Assinatura getByDono(Long donoId);
}
