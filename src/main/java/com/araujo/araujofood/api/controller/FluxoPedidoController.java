package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/confirmacao")
    public void confirmar(@PathVariable String codigoPedido) {
        fluxoPedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/cancelamento")
    public void cancelar(@PathVariable String codigoPedido) {
        fluxoPedidoService.cancelar(codigoPedido);
    }

    @PutMapping("/entrega")
    public void entregar(@PathVariable String codigoPedido) {
        fluxoPedidoService.entregar(codigoPedido);
    }
}
