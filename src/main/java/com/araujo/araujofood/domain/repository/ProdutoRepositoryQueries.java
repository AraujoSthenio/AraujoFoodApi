package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);

}
