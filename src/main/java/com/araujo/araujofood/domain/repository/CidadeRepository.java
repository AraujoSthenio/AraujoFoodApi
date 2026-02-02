package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Cidade;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}
