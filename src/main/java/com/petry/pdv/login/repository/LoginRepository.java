package com.petry.pdv.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petry.pdv.login.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

}
