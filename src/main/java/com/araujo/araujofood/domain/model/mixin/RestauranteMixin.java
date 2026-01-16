package com.araujo.araujofood.domain.model.mixin;

import com.araujo.araujofood.domain.model.Cozinha;
import com.araujo.araujofood.domain.model.Endereco;
import com.araujo.araujofood.domain.model.FormaPagamento;
import com.araujo.araujofood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
