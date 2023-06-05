package com.cadastro.profissionalestabelecimento.infra.controller;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.ProfissionalService;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
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
@RequestMapping("profissional")
@RequiredArgsConstructor
@Tag(name = "Profissionais", description = "API REST Profissionais")
public class ProfissionalController {

    public final ProfissionalService profissionalService;

    @PostMapping("/cadastrar-profissional")
    @Operation(summary = "Cadastrar um profissional")
    @ApiResponse(responseCode = "201", description = "created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfissionalDto.class))
    })
    public ResponseEntity<ProfissionalDto> cadastrarProfissional(
            @Validated @RequestBody ProfissionalDto profissionalDto) throws ProfissionalException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profissionalService.cadastrarProfissional(profissionalDto));
    }

    @PutMapping("/atualizar-profissional")
    @Operation(summary = "Atualizar um profissional por CPF")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfissionalDto.class))
    })
    public ResponseEntity<ProfissionalDto> atualizarProfissional(@RequestParam String cpfProfissional,
                                                                       @Validated @RequestBody ProfissionalDto profissionalDto)
                                                                    throws ProfissionalException {

        return ResponseEntity.ok(profissionalService.atualizarProfissional(cpfProfissional, profissionalDto));
    }

    @GetMapping
    @Operation(summary = "Retornar um profissional por CPF")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProfissionalDto.class))
    })
    public ResponseEntity<ProfissionalDto> buscarProfissionalPorCpf(@RequestParam String cpfProfissional)
                                                                            throws ProfissionalException {
        return ResponseEntity.ok(profissionalService.buscarProfissionalPorCpf(cpfProfissional));
    }

    @DeleteMapping("/excluir-profissional")
    @Operation(summary = "Deletar profissional passando CPF")
    @ApiResponse(responseCode = "200", description = "OK")
    public void excluirProfissional(@RequestParam String cpfProfissional) throws ProfissionalException {
        profissionalService.excluirProfissional(cpfProfissional);
    }
}
