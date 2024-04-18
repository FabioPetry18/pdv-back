package com.petry.pdv.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petry.pdv.funcionario.entity.Funcionario;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
		List<Funcionario> findByIdLoja(Long idLoja);
}
