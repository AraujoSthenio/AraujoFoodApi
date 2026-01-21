package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.EstadoInputDisassembler;
import com.araujo.araujofood.api.assembler.EstadoModelAssembler;
import com.araujo.araujofood.api.model.EstadoModel;
import com.araujo.araujofood.api.model.input.EstadoInput;
import com.araujo.araujofood.domain.model.Estado;
import com.araujo.araujofood.domain.repository.EstadoRepository;
import com.araujo.araujofood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(cadastroEstadoService.buscar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel salvar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable @Valid Long estadoId, @RequestBody EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstadoService.buscar(estadoId);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.remover(estadoId);
    }

}
