package com.petry.pdv.endereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petry.pdv.endereco.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
 
	
	@Query(value = "SELECT IFNULL(MAX(id), 0) AS ultimo_id FROM endereco WHERE idcliente = ?1", nativeQuery = true)
	Long getUltimoIndexEndereco(Long idCliente);

	@Query(value = "SELECT * FROM endereco WHERE idcliente = ?1", nativeQuery = true)
	List<Endereco> findByIdClienteAndIdLoja(Long id);
}
