package com.cadastro.profissionalestabelecimento.core.dto;

public record EstabelecimentoDto(Long id,
                                 String nome,
                                 String cnpj,
                                 String endereco,
                                 String telefone,
                                 String especialidade) {
}
