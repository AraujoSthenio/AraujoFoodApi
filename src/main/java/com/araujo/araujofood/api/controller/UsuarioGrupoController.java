package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.GrupoModelAssembler;
import com.araujo.araujofood.api.model.GrupoModel;
import com.araujo.araujofood.domain.model.Grupo;
import com.araujo.araujofood.domain.model.Usuario;
import com.araujo.araujofood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId,
                            @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId,
                            @PathVariable Long grupoId) {
        usuarioService.removerGrupo(usuarioId, grupoId);
    }

}
