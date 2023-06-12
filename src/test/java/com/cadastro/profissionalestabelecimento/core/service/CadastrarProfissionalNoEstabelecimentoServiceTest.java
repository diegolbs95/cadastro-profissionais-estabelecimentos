package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.impl.CadastrarProfissionalNoEstabelecimentoServiceImpl;
import com.cadastro.profissionalestabelecimento.core.service.impl.EstabelecimentoServiceImpl;
import com.cadastro.profissionalestabelecimento.core.service.impl.ProfissionalServiceImpl;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.EstabelecimentoProfissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.EstabelecimentoProfissionalRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarProfissionalNoEstabelecimentoServiceTest {

//    @Mock
//    EstabelecimentoServiceImpl estabelecimentoService;
//    @Mock
//    ProfissionalRepository profissionalRepository;
//    @Mock
//    ProfissionalServiceImpl profissionalService;
//    @Mock
//    EstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;
//    @Mock
//    EstabelecimentoProfissional estabelecimentoProfissional;
//
//    @InjectMocks
//    CadastrarProfissionalNoEstabelecimentoServiceImpl cadastrarProfissionalNoEstabelecimentoService;
//
//    private EstabelecimentoProfissionalDto estabelecimentoProfissionalDto;
//    private final List<Profissional> listProfissionais = new ArrayList<>();
//    private Profissional profissional;
//    private Estabelecimento estabelecimento;
//
//    @BeforeEach
//    void setUp() {
//        estabelecimentoProfissionalDto =
//                new EstabelecimentoProfissionalDto("131214546456",
//                "797897854");
//
//        estabelecimento = Estabelecimento.builder()
//                .cnpj("22312331322312")
//                .endereco("Sesquecentenario")
//                .especialidade("Bolo")
//                .nome("PanificadoraDL")
//                .build();
//
//        profissional = Profissional.builder()
//                .cpf("456.584.789-96")
//                .endereco("Rua Maria Helena Gonzaga")
//                .especialidade("Bolo")
//                .telefoneCelular("81975698899")
//                .nome("Diego Luis")
//                .urlFoto("http:localhost:8080")
//                .build();
//
//        estabelecimentoProfissional.setEstabelecimento(estabelecimento);
//        estabelecimentoProfissional.setProfissional(profissional);
//        listProfissionais.add(profissional);
//        estabelecimentoProfissionalDto =
//                new EstabelecimentoProfissionalDto("23123",
//                        "44568974215");
//    }
//
//    @Test
//    void cadastrarProfissionalEstabelecimentoTest() throws ProfissionalException,
//                                                        EstabelecimentoException {
//
//        when(estabelecimentoService.buscarEstabelecimentoOuFalhar(anyString()))
//                .thenReturn(estabelecimento);
//        when(profissionalService.buscarProfissionalOuFalhar(anyString()))
//                .thenReturn(profissional);
//
//        var result = cadastrarProfissionalNoEstabelecimentoService
//                .cadastrarProfissionalEstabelecimento(estabelecimentoProfissionalDto);
//
//        verify(estabelecimentoProfissionalRepository, times(1))
//                .save(any());
//        assertEquals("PROFISSIONAL_CADASTRADO_NO_ESTABELECIMENTO",
//                result);
//    }
//
//    @Test
//    void cadastrarProfissionalEstabelecimentoValidandoMesmoProfissional() throws EstabelecimentoException,
//                                                                            ProfissionalException {
//
//        listProfissionais.add(profissional);
//
//        when(estabelecimentoService.buscarEstabelecimentoOuFalhar(anyString()))
//                .thenReturn(estabelecimento);
//        when(profissionalService.buscarProfissionalOuFalhar(anyString()))
//                .thenReturn(profissional);
//
//        Assertions.assertThrows(EstabelecimentoException.class,
//                ()-> cadastrarProfissionalNoEstabelecimentoService
//                        .cadastrarProfissionalEstabelecimento(estabelecimentoProfissionalDto));
//    }
//
//    @Test
//    void castrarProfissionalEstabelecimentoValidandoMesmaEspecialidade() throws ProfissionalException,
//                                                                            EstabelecimentoException {
//        var profissional02 = Profissional.builder()
//                .cpf("897.456.123.12")
//                .endereco("Rua Ribeirao")
//                .especialidade("bolo")
//                .nome("Theo Luis")
//                .telefoneCelular("81978693215")
//                .urlFoto("http:localhost:8080")
//                .build();
//
//        when(estabelecimentoService.buscarEstabelecimentoOuFalhar(anyString()))
//                .thenReturn(estabelecimento);
//        when(profissionalService.buscarProfissionalOuFalhar(anyString()))
//                .thenReturn(profissional02);
//
//        Assertions.assertThrows(EstabelecimentoException.class,
//                ()-> cadastrarProfissionalNoEstabelecimentoService
//                        .cadastrarProfissionalEstabelecimento(estabelecimentoProfissionalDto));
//    }
//
//    @Test
//    void validarSeProfissionalJaEstaCadastradoEmEstabelecimento() throws EstabelecimentoException, ProfissionalException {
//        var profissional02 = Profissional.builder()
//                .cpf("897.456.123.12")
//                .endereco("Rua Ribeirao")
//                .especialidade("bolo")
//                .nome("Theo Luis")
//                .telefoneCelular("81978693215")
//                .urlFoto("http:localhost:8080")
//                .build();
//
//        when(estabelecimentoService.buscarEstabelecimentoOuFalhar(anyString()))
//                .thenReturn(estabelecimento);
//        when(profissionalService.buscarProfissionalOuFalhar(anyString()))
//                .thenReturn(profissional02);
//
//        Assertions.assertThrows(ProfissionalException.class,
//                ()-> cadastrarProfissionalNoEstabelecimentoService
//                        .cadastrarProfissionalEstabelecimento(estabelecimentoProfissionalDto));
//    }
}