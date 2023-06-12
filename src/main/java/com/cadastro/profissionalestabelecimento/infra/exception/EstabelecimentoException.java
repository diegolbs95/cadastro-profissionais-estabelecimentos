package com.cadastro.profissionalestabelecimento.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EstabelecimentoException extends RuntimeException {

    public EstabelecimentoException(String message) {
        super(message);
    }
}
