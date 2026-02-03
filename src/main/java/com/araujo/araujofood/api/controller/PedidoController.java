package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.PedidoInputDisassembler;
import com.araujo.araujofood.api.assembler.PedidoModelAssembler;
import com.araujo.araujofood.api.assembler.PedidoResumoModelAssembler;
import com.araujo.araujofood.api.model.PedidoModel;
import com.araujo.araujofood.api.model.PedidoResumoModel;
import com.araujo.araujofood.api.model.input.PedidoInput;
import com.araujo.araujofood.domain.exception.EntidadeNaoEncontradaException;
import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.model.Pedido;
import com.araujo.araujofood.domain.model.StatusPedido;
import com.araujo.araujofood.domain.model.Usuario;
import com.araujo.araujofood.domain.repository.PedidoRepository;
import com.araujo.araujofood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<PedidoResumoModel> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoResumoModelAssembler.toCollectionModel(pedidos);
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

}
