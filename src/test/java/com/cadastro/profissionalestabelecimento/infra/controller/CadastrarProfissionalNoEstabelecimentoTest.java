package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.impl.CadastrarProfissionalNoEstabelecimentoServiceImpl;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarProfissionalNoEstabelecimentoTest {

    @Mock
    CadastrarProfissionalNoEstabelecimentoServiceImpl estabelecimentoProfissionalService;

    @InjectMocks
    CadastrarProfissionalNoEstabelecimentoController controller;

    @Test
    void cadastrarEstabelecimentoProfissional() throws ProfissionalException, EstabelecimentoException {

        var estabelecimentoProfissionalDto = new EstabelecimentoProfissionalDto(
                "97052543000194", "45658478996");

        when(estabelecimentoProfissionalService.cadastrarProfissionalEstabelecimento(any()))
                .thenReturn("PROFISSIONAL_CADASTRADO_NO_ESTABELECIMENTO");

        var result = controller
                .cadastrarEstabelecimentoProfissional(estabelecimentoProfissionalDto);

        assertEquals("PROFISSIONAL_CADASTRADO_NO_ESTABELECIMENTO", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}