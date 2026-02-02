package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Permissao;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
