package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.araujo.araujofood.domain.model.FormaPagamento;
import com.araujo.araujofood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroFormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento buscar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
