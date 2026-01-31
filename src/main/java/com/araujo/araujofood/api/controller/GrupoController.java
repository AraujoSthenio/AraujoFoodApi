package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.GrupoInputDisassembler;
import com.araujo.araujofood.api.assembler.GrupoModelAssembler;
import com.araujo.araujofood.api.model.GrupoModel;
import com.araujo.araujofood.api.model.input.GrupoInput;
import com.araujo.araujofood.domain.exception.GrupoNaoEncontradoException;
import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.model.Grupo;
import com.araujo.araujofood.domain.repository.GrupoRepository;
import com.araujo.araujofood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();
        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
            return grupoModelAssembler.toModel(grupoService.salvar(grupo));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{grupoId}")
    public GrupoModel salvar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        try {
            Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
            grupoInputDisassembler.copyToDomainModel(grupoInput, grupoAtual);
            return grupoModelAssembler.toModel(grupoService.salvar(grupoAtual));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.remover(grupoId);
    }

}
