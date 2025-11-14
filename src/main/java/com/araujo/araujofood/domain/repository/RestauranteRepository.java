package com.araujo.araujofood.domain.repository;

import com.araujo.araujofood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);

}
