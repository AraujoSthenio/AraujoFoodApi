package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.PedidoInputDisassembler;
import com.araujo.araujofood.api.assembler.PedidoModelAssembler;
import com.araujo.araujofood.api.assembler.PedidoResumoModelAssembler;
import com.araujo.araujofood.api.model.PedidoModel;
import com.araujo.araujofood.api.model.PedidoResumoModel;
import com.araujo.araujofood.api.model.input.PedidoInput;
import com.araujo.araujofood.core.data.PageableTranslator;
import com.araujo.araujofood.domain.exception.EntidadeNaoEncontradaException;
import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.model.Pedido;
import com.araujo.araujofood.domain.model.Usuario;
import com.araujo.araujofood.domain.repository.PedidoRepository;
import com.araujo.araujofood.domain.filter.PedidoFilter;
import com.araujo.araujofood.domain.service.EmissaoPedidoService;
import com.araujo.araujofood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(@PageableDefault(size = 10) PedidoFilter filtro, Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());

        return new PageImpl<>(pedidosModel, pageable, pedidosPage.getTotalElements());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);
            novoPedido = pedidoService.emitir(novoPedido);
            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "cliente.nome", "cliente.nome"
        );

        return PageableTranslator.translator(apiPageable, mapeamento);
    }

}
