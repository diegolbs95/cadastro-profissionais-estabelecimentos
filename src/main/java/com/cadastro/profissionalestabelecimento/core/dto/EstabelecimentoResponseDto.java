package com.cadastro.profissionalestabelecimento.core.dto;

import java.util.List;

public record EstabelecimentoResponseDto(String nome,
                                         String cnpj,
                                         String endereco,
                                         String telefone,
                                         String especialidade,
                                         List<ProfissionalDto> profissionalDtoList) {
}
