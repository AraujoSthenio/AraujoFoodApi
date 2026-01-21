package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.CidadeInputDisassembler;
import com.araujo.araujofood.api.assembler.CidadeModelAssembler;
import com.araujo.araujofood.api.model.CidadeModel;
import com.araujo.araujofood.api.model.input.CidadeInput;
import com.araujo.araujofood.domain.exception.EstadoNaoEncontradoException;
import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.model.Cidade;
import com.araujo.araujofood.domain.repository.CidadeRepository;
import com.araujo.araujofood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return cidadeModelAssembler.toModel(cadastroCidadeService.buscar(cidadeId));
    }

    @PostMapping
    public CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            return cidadeModelAssembler.toModel(cadastroCidadeService.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel alterar(@PathVariable @Valid Long cidadeId, @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidadeService.buscar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            return cidadeModelAssembler.toModel(cadastroCidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.remover(cidadeId);
    }

}
