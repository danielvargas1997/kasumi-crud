package com.kasumi.kasumi_crud.repository;
// src/main/java/com/kasumi/repository/ClienteRepository.java


import org.springframework.data.jpa.repository.JpaRepository;
import com.kasumi.kasumi_crud.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }
