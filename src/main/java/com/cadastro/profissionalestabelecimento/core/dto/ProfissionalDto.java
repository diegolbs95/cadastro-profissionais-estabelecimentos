package com.cadastro.profissionalestabelecimento.core.dto;

public record ProfissionalDto(Long id,
                              String nome,
                              String cpf,
                              String endereco,
                              String telefoneCelular,
                              String telefoneFixo,
                              String especialidade,
                              String urlFoto) {
}
