package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.exception.PermissaoNaoEncontradaException;
import com.araujo.araujofood.domain.model.Permissao;
import com.araujo.araujofood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
