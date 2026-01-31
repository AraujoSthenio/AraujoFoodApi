package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.FormaPagamentoInputDisassembler;
import com.araujo.araujofood.api.assembler.FormaPagamentoModelAssembler;
import com.araujo.araujofood.api.model.FormaPagamentoModel;
import com.araujo.araujofood.api.model.input.FormaPagamentoInput;
import com.araujo.araujofood.domain.model.FormaPagamento;
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
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

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
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainModel(formaPAgamentoInput);
        formaPagamento = formaPagamentoService.salvar(formaPagamento);
        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel Atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPAgamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscar(formaPagamentoId);
        formaPagamentoInputDisassembler.copyToDomainModel(formaPAgamentoInput, formaPagamentoAtual);
        formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);
        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.remover(formaPagamentoId);
    }


}
