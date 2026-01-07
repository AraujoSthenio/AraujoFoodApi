package com.araujo.araujofood.domain.exception;

public class NegocioException extends RuntimeException {

    private static final long seriaVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
