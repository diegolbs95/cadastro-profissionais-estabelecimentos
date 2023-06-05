package com.cadastro.profissionalestabelecimento.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProfissionalException extends Exception{

    public ProfissionalException(String message) {
        super(message);
    }
}
