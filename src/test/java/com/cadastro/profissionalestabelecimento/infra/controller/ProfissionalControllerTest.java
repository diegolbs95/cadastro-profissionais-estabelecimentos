package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.impl.ProfissionalServiceImpl;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfissionalControllerTest {

    @Mock
    ProfissionalServiceImpl profissionalService;

    @InjectMocks
    ProfissionalController profissionalController;

    private ProfissionalDto profissionalDto;

    @BeforeEach
    void setUp() {

        profissionalDto = new ProfissionalDto(1L,
                    "Diego Luis",
                    "45658478996",
                    "Rua Maria Helena Gonzaga",
                "81975698899",
                "81975698899",
                "Bolo",
                "http:localhost:8080");
    }

    @Test
    void cadastrarProfissional() throws ProfissionalException {

       when(profissionalService.cadastrarProfissional(any())).thenReturn(profissionalDto);

       var result = profissionalController
                                             .cadastrarProfissional(profissionalDto);

       assertEquals(profissionalDto, result.getBody());
       assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

//    @Test
//    void atualizarProfissional() throws ProfissionalException {
//
//        when(profissionalService.atualizarProfissional(anyString(), any())).thenReturn(profissionalDto);
//
//        var result = profissionalController
//                            .atualizarProfissional("45658478996", profissionalDto);
//
//        assertEquals(profissionalDto, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }

//    @Test
//    void buscarProfissionalPorCpf() throws ProfissionalException {
//
//        when(profissionalService.buscarProfissionalPorCpf(anyString())).thenReturn(profissionalDto);
//
//        var result = profissionalController
//                                                .buscarProfissionalPorCpf("45658478996");
//
//        assertEquals(profissionalDto, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }

    @Test
    void excluirProfissional() throws ProfissionalException {

        profissionalController.excluirProfissional("45658478996");

        verify(profissionalService, times(1)).excluirProfissional(anyString());
    }
}