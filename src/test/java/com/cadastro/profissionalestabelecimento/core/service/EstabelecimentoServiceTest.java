package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoResponseDto;
import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.impl.EstabelecimentoServiceImpl;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.EstabelecimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstabelecimentoServiceTest {

    @Mock
    EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    EstabelecimentoServiceImpl estabelecimentoService;

    private EstabelecimentoDto estabelecimentoDto;
    private Estabelecimento estabelecimento;
    private final List<Estabelecimento> listEstabelecimentos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        estabelecimento = Estabelecimento.builder()
                .id(1L)
                .endereco("Rua Sao Francisco")
                .telefone("81978693512")
                .especialidade("Bolo")
                .nome("PanificadoraDL")
                .cnpj("97052543000194")
                .build();
        estabelecimentoDto = new EstabelecimentoDto(1L,
                        "PanificadoraDL",
                        "97052543000194",
                    "Rua Sao Francisco",
                    "81978693512",
                "Bolo");
    }

    @Test
    void cadastrarEstabelecimento() throws EstabelecimentoException {

        when(estabelecimentoRepository.findAll()).thenReturn(listEstabelecimentos);

        var result = estabelecimentoService.cadastrarEstabelecimento(estabelecimentoDto);

        verify(estabelecimentoRepository, times(1)).save(any());
        assertEquals(estabelecimentoDto, result);
    }

    @Test
    void cadastrarEstabelecimentoValidarMesmoEstabelecimento() {

        listEstabelecimentos.add(estabelecimento);
        when(estabelecimentoRepository.findAll()).thenReturn(listEstabelecimentos);

        Assertions.assertThrows(EstabelecimentoException.class, ()->
            estabelecimentoService.cadastrarEstabelecimento(estabelecimentoDto));
    }

    @Test
    void atualizarEstabelecimento() throws EstabelecimentoException {

        when(estabelecimentoRepository.findByCnpj(anyString()))
                .thenReturn(Optional.ofNullable(estabelecimento));

        var result = estabelecimentoService
                .atualizarEstabelecimento("97052543000194", estabelecimentoDto);

        verify(estabelecimentoRepository, times(1)).save(any());
        assertEquals(estabelecimentoDto, result);
    }

    @Test
    void buscarEstabelecimentoOuFalharTest() {

        when(estabelecimentoRepository.findByCnpj(anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EstabelecimentoException.class,
                () -> estabelecimentoService.buscarEstabelecimentoOuFalhar("97052543000194"));
    }

    @Test
    void deletarEstabelecimento() throws EstabelecimentoException {

        when(estabelecimentoRepository.findByCnpj(anyString()))
                .thenReturn(Optional.ofNullable(estabelecimento));

        estabelecimentoService.deletarEstabelecimento("97052543000194");

        verify(estabelecimentoRepository, times(1)).delete(any());
    }

    @Test
    void buscarEstabelecimentoPorCnpjTest() throws EstabelecimentoException {
        var listProfissionais = new ArrayList<Profissional>();
        var listProfissionaisDto = new ArrayList<ProfissionalDto>();
        listProfissionais.add(Profissional.builder()
                        .id(1L )
                        .telefoneCelular("81978521456")
                        .telefoneFixo("81978521456")
                        .nome("Diego Luis")
                        .cpf("11247185289")
                        .especialidade("Bolo")
                        .endereco("Rua Alemao")
                        .urlFoto("http:localhost:8080")
                        .build());
        estabelecimento.setProfissionais(listProfissionais);

        listProfissionaisDto.add(new ProfissionalDto(1L,
                "Diego Luis",
                "11247185289",
                "Rua Alemao",
                "81978521456",
                "81978521456",
                "Bolo",
                "http:localhost:8080"));

        var response = new EstabelecimentoResponseDto("PanificadoraDL",
                        "97052543000194",
                    "Rua Sao Francisco",
                    "81978693512",
                "Bolo",
                            listProfissionaisDto);

        when(estabelecimentoRepository.findByCnpj(anyString()))
                .thenReturn(Optional.ofNullable(estabelecimento));

        var result = estabelecimentoService
                .buscarEstabelecimentoPorCnpj("97052543000194");

        assertEquals(response, result);
    }
}