package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoResponseDto;
import com.cadastro.profissionalestabelecimento.core.service.EstabelecimentoService;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estabelecimento")
@RequiredArgsConstructor
@Tag(name = "Estabelecimento", description = "API REST Estabelecimentos")
public class EstabelecimentoController {

    public final EstabelecimentoService estabelecimentoService;

    @PostMapping("/cadastrar-estabelecimento")
    @Operation(summary = "Cadastrar um estabelecimento")
    @ApiResponse(responseCode = "201", description = "created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EstabelecimentoDto.class))
    })
    public ResponseEntity<EstabelecimentoDto> cadastrarEstabelecimento(
            @Validated @RequestBody EstabelecimentoDto estabelecimentoDto) throws EstabelecimentoException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estabelecimentoService.cadastrarEstabelecimento(estabelecimentoDto));
    }

    @PutMapping("/atualizar-estabelecimento")
    @Operation(summary = "Atualizar u estabelecimento passando CNPJ")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EstabelecimentoDto.class))
    })
    public ResponseEntity<EstabelecimentoDto> atualizarEstabelecimento(@RequestParam String cnpjEstabelecimento,
                                                                       @Validated @RequestBody EstabelecimentoDto estabelecimentoDto)
                                                                        throws EstabelecimentoException {

        return ResponseEntity.ok(estabelecimentoService.atualizarEstabelecimento(cnpjEstabelecimento, estabelecimentoDto));
    }

    @GetMapping
    @Operation(summary = "Retornar um estabelecimento passando CNPJ")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EstabelecimentoResponseDto.class))
    })
    public ResponseEntity<EstabelecimentoResponseDto> buscarEstabelecimentoPorCnpj(@RequestParam String cnpjEstabelecimento)
                                                                            throws EstabelecimentoException {
        return ResponseEntity.ok(estabelecimentoService.buscarEstabelecimentoPorCnpj(cnpjEstabelecimento));
    }

    @DeleteMapping("/excluir-estabelecimento")
    @Operation(summary = "Deletar estabelecimento passando CNPJ")
    @ApiResponse(responseCode = "200", description = "OK")
    public void excluirEstabelecimento(@RequestParam String cnpj) throws EstabelecimentoException {
        estabelecimentoService.deletarEstabelecimento(cnpj);
    }
}
