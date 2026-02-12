package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.ProdutoInputDisassembler;
import com.araujo.araujofood.api.assembler.ProdutoModelAssembler;
import com.araujo.araujofood.api.model.ProdutoModel;
import com.araujo.araujofood.api.model.input.ProdutoInput;
import com.araujo.araujofood.domain.model.Produto;
import com.araujo.araujofood.domain.model.Restaurante;
import com.araujo.araujofood.domain.repository.ProdutoRepository;
import com.araujo.araujofood.domain.service.CadastroProdutoService;
import com.araujo.araujofood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService produtoService;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        List<Produto> produtos;
        if (incluirInativos) {
            produtos = produtoRepository.findByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId,
                               @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        produto = produtoService.salvar(produto);
        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = produtoService.salvar(produtoAtual);
        return produtoModelAssembler.toModel(produtoAtual);
    }

}
