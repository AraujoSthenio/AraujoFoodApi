package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.exception.NegocioException;
import com.araujo.araujofood.domain.exception.UsuarioNaoEncontradoException;
import com.araujo.araujofood.domain.model.Grupo;
import com.araujo.araujofood.domain.model.Usuario;
import com.araujo.araujofood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    public static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removido, pois está em uso";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService grupoService;

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void removerGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.removerGrupo(grupo);
    }

}
