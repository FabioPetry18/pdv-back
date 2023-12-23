package com.petry.pdv.assinatura.repository;


import com.petry.pdv.assinatura.entity.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
}
