package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.exception.ProdutoNaoEncontradoException;
import com.araujo.araujofood.domain.model.Produto;
import com.araujo.araujofood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

}
