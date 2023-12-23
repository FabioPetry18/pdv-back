package com.petry.pdv.configuracao.repository;

import com.petry.pdv.configuracao.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao,Long> {
}
