package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.FormaPagamento;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

}
