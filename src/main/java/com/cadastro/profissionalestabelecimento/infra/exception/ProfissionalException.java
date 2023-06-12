package com.cadastro.profissionalestabelecimento.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProfissionalException extends RuntimeException{

    public ProfissionalException(String message) {
        super(message);
    }
}
