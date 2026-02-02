package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.UsuarioModelAssembler;
import com.araujo.araujofood.api.model.UsuarioModel;
import com.araujo.araujofood.domain.model.Restaurante;
import com.araujo.araujofood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarResponsvel(@PathVariable Long restauranteId,
                                   @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarResponsavel(@PathVariable Long restauranteId,
                                   @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
    }
}
