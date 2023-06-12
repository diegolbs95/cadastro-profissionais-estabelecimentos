package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.impl.ProfissionalServiceImpl;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.ProfissionalRepository;
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
class ProfissionalServiceTest {

    @Mock
    ProfissionalRepository profissionalRepository;

    @InjectMocks
    ProfissionalServiceImpl profissionalService;

    private final List<Profissional> listProfissionais = new ArrayList<>();
    private Profissional profissional;
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

        profissional = Profissional.builder()
                .id(1L)
                .cpf("45658478996")
                .endereco("Rua Maria Helena Gonzaga")
                .especialidade("Bolo")
                .telefoneCelular("81975698899")
                .telefoneFixo("81975698899")
                .nome("Diego Luis")
                .urlFoto("http:localhost:8080")
                .build();

    }

    @Test
    void cadastrarProfissional() throws ProfissionalException {

        when(profissionalRepository.findAll()).thenReturn(new ArrayList<>());

        var result = profissionalService
                .cadastrarProfissional(profissionalDto);

        verify(profissionalRepository, times(1)).save(any());
        assertEquals(profissionalDto, result);
    }

    @Test
    void validarExisteMesmoProfissional(){

        listProfissionais.add(profissional);
        when(profissionalRepository.findAll()).thenReturn(listProfissionais);

        Assertions.assertThrows(ProfissionalException.class, ()->
                profissionalService.cadastrarProfissional(profissionalDto));
    }

    @Test
    void atualizarProfissional() throws ProfissionalException {

        when(profissionalRepository.findByCpf(anyString()))
                .thenReturn(Optional.ofNullable(profissional));

        var result = profissionalService
                .atualizarProfissional("45658478996", profissionalDto);

        verify(profissionalRepository, times(1)).save(any());
        assertEquals(profissionalDto, result);
    }

    @Test
    void profissioalNaoEncontratoNoBanco(){
        when(profissionalRepository.findByCpf(anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ProfissionalException.class,
                () -> profissionalService.buscarProfissionalOuFalhar("45658478996"));
    }

    @Test
    void excluirProfissional() throws ProfissionalException {

        when(profissionalRepository.findByCpf(anyString()))
                .thenReturn(Optional.ofNullable(profissional));

        profissionalService.excluirProfissional("45658478996");

        verify(profissionalRepository, times(1)).delete(any());
    }
//
//    @Test
//    void buscarProfissionalPorCpfTest() throws ProfissionalException {
//
//        when(profissionalRepository.findByCpf(anyString()))
//                .thenReturn(Optional.ofNullable(profissional));
//
//        var result = profissionalService
//                .buscarProfissionalPorCpf("45658478996");
//
//        assertEquals(profissionalDto, result);
//    }
}