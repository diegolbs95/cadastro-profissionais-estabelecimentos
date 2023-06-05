package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoResponseDto;
import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.EstabelecimentoService;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstabelecimentoControllerTest {

    @Mock
    EstabelecimentoService estabelecimentoService;

    @InjectMocks
    EstabelecimentoController estabelecimentoController;

    private EstabelecimentoDto estabelecimentoDto;
    private EstabelecimentoResponseDto estabelecimentoResponseDto;
    private final List<ProfissionalDto> listProfissionaisDto = new ArrayList<>();

    @BeforeEach
    void setUp() {
        var profissionalDto = new ProfissionalDto(1L,
                "Diego Luis",
                "45658478996",
                "Rua Maria Helena Gonzaga",
                "81975698899",
                "81975698899",
                "Bolo",
                "http:localhost:8080");
        listProfissionaisDto.add(profissionalDto);

        estabelecimentoResponseDto = new EstabelecimentoResponseDto("PanificadoraDL",
                "97052543000194",
                "Rua Sao Francisco",
                "81978693512",
                "Bolo",
                listProfissionaisDto);

        estabelecimentoDto = new EstabelecimentoDto(1L,
                "PanificadoraDL",
                "97052543000194",
                "Rua Sao Francisco",
                "81978693512",
                "Bolo");
    }

    @Test
    void cadastrarEstabelecimento() throws EstabelecimentoException {

        when(estabelecimentoService.cadastrarEstabelecimento(any())).thenReturn(estabelecimentoDto);

        var result = estabelecimentoController
                .cadastrarEstabelecimento(estabelecimentoDto);

        assertEquals(estabelecimentoDto, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void atualizarEstabelecimento() throws EstabelecimentoException {

        when(estabelecimentoService.atualizarEstabelecimento(anyString(), any())).thenReturn(estabelecimentoDto);

        var result = estabelecimentoController
                .atualizarEstabelecimento("97052543000194", estabelecimentoDto);

        assertEquals(estabelecimentoDto, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void buscarEstabelecimentoPorCnpj() throws EstabelecimentoException {
        when(estabelecimentoService.buscarEstabelecimentoPorCnpj(anyString()))
                .thenReturn(estabelecimentoResponseDto);

        var result = estabelecimentoController
                .buscarEstabelecimentoPorCnpj("97052543000194");

        assertEquals(estabelecimentoResponseDto, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void excluirEstabelecimento() throws EstabelecimentoException {

        estabelecimentoController.excluirEstabelecimento("97052543000194");

        verify(estabelecimentoService, times(1)).deletarEstabelecimento(anyString());
    }
}