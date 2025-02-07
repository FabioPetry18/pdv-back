package com.petry.pdv.assinatura.repository;


import com.petry.pdv.assinatura.entity.Assinatura;

import com.petry.pdv.dono.entity.Proprietario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
	
	@Query(value = "SELECT * FROM PDV.ASSINATURA ASSI INNER JOIN PDV.DONO DON ON DON.IDDONO = ASSI.IDDONO where don.iddono = :donoId", nativeQuery =  true)
	Assinatura getByDono(Long donoId);
}
