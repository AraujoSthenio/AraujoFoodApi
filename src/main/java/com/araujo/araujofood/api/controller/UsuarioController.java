package com.araujo.araujofood.api.controller;

import com.araujo.araujofood.api.assembler.UsuarioInputDisassembler;
import com.araujo.araujofood.api.assembler.UsuarioModelAssembler;
import com.araujo.araujofood.api.model.UsuarioModel;
import com.araujo.araujofood.api.model.input.SenhaInput;
import com.araujo.araujofood.api.model.input.UsuarioComSenhaInput;
import com.araujo.araujofood.api.model.input.UsuarioInput;
import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.exception.UsuarioNaoEncontradoException;
import com.araujo.araujofood.domain.model.Usuario;
import com.araujo.araujofood.domain.repository.UsuarioRepository;
import com.araujo.araujofood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioModel> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioModelAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        try {
            Usuario usuario = usuarioInputDisassembler.toDomainObjetc(usuarioInput);
            return usuarioModelAssembler.toModel(usuarioService.salvar(usuario));
        } catch (UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel alterar(@PathVariable Long usuarioId,
                               @RequestBody @Valid UsuarioInput usuarioInput) {
        try {
            Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
            usuarioInputDisassembler.copyToDomainModel(usuarioInput, usuarioAtual);
            return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioAtual));
        } catch (UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
