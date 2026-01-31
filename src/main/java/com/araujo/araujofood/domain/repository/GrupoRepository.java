package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Grupo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
