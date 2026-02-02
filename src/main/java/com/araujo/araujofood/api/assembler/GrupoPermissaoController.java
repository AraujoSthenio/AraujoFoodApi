package com.araujo.araujofood.api.assembler;

import com.araujo.araujofood.api.model.PermissaoModel;
import com.araujo.araujofood.domain.model.Grupo;
import com.araujo.araujofood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId,
                            @PathVariable Long permissaoId) {
        grupoService.adicionarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId,
                            @PathVariable Long permissaoId) {
        grupoService.removerPermissao(grupoId, permissaoId);
    }
}
