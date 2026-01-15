package com.araujo.araujofood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluirDescricao {

    String message() default "descrição obrigatória inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valorField();

    String descricaoField();

    String descricaoObrigatoria();

}
