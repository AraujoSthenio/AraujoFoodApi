package com.araujo.araujofood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long permissaoId) {
        this(String.format("Não existe um cadastro de permissao de pagamento com código %d", permissaoId));
    }

}
