package com.araujo.araujofood.api.assembler;

import com.araujo.araujofood.api.model.input.CidadeInput;
import com.araujo.araujofood.domain.model.Cidade;
import com.araujo.araujofood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        // Para evitar HibernateException
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }
}
