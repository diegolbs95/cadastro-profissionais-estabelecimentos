package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.CadastrarProfissionalNoEstabelecimentoService;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cadastrar-estabelecimento-profissional")
@RequiredArgsConstructor
@Tag(name = "Cadastrar-Estabelecimento-Profissional",
        description = "API REST cadastramento de profissionais no estabelecimento")
public class CadastrarProfissionalNoEstabelecimentoController {

    private final CadastrarProfissionalNoEstabelecimentoService cadastrarProfissionalNoEstabelecimentoService;

    @PostMapping
    @Operation(summary = "Cadastramento de dados do profissional no estabelecimento")
    @ApiResponse(responseCode = "201", description = "created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    public ResponseEntity<String> cadastrarEstabelecimentoProfissional(@RequestBody EstabelecimentoProfissionalDto
                                                                                   estabelecimentoProfissionalDto)
                                                            throws ProfissionalException, EstabelecimentoException {

        return ResponseEntity.ok(cadastrarProfissionalNoEstabelecimentoService
                .cadastrarProfissionalEstabelecimento(estabelecimentoProfissionalDto));
    }
}
