package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.exception.EntidadeEmUsoException;
import com.araujo.araujofood.domain.exception.EntidadeNaoEncontradaException;
import com.araujo.araujofood.domain.model.Cozinha;
import com.araujo.araujofood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
    public static final String MSG_COZINHA_EM_USO = "Não existe um cadastro de cozinha com código %d";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
        }
    }
}
