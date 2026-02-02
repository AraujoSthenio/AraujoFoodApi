package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Estado;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}
