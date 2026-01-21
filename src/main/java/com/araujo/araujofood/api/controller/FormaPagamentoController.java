package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.FormaPagamentoInputDisassembler;
import com.araujo.araujofood.api.assembler.FormaPagamentoModelAssembler;
import com.araujo.araujofood.api.model.FormaPagamentoModel;
import com.araujo.araujofood.api.model.input.FormaPagamentoInput;
import com.araujo.araujofood.domain.repository.FormaPagamentoRepository;
import com.araujo.araujofood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler FormaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.buscar(formaPagamentoId));
    }

    @PostMapping
    public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPAgamentoInput) {
        //todo: Continuar implementação endpoint de salvar
        return null;
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel Atualizar(@PathVariable @Valid Long formaPagamentoId,
                                         @RequestBody FormaPagamentoInput formaPAgamentoInput) {
        //todo: Continuar implementação endpoint de alterar
        return null;
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        //todo: Continuar implementação endpoint de exclusão
    }


}
