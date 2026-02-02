package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByNome(String nome);

    List<Cozinha> findTodasByNomeContaining(String nome);

}
