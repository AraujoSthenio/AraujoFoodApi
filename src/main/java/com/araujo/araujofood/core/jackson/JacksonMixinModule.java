package com.araujo.araujofood.core.jackson;

import com.araujo.araujofood.domain.model.Cidade;
import com.araujo.araujofood.domain.model.Cozinha;
import com.araujo.araujofood.domain.model.Restaurante;
import com.araujo.araujofood.domain.model.mixin.CidadeMixin;
import com.araujo.araujofood.domain.model.mixin.CozinhaMixin;
import com.araujo.araujofood.domain.model.mixin.RestauranteMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
