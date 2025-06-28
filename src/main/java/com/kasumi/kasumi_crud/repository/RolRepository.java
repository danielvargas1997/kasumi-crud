package com.kasumi.kasumi_crud.repository;
// src/main/java/com/kasumi/repository/RolRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import com.kasumi.kasumi_crud.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> { }
