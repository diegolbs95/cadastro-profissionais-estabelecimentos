package com.cadastro.profissionalestabelecimento.infra.factory;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EstabelecimentoFactory {

    public static Estabelecimento criarEstabelecimento(EstabelecimentoDto estabelecimentoDto){
        return Estabelecimento.builder()
                .id(estabelecimentoDto.id())
                .nome(estabelecimentoDto.nome())
                .endereco(estabelecimentoDto.endereco())
                .cnpj(estabelecimentoDto.cnpj())
                .especialidade(estabelecimentoDto.especialidade())
                .telefone(estabelecimentoDto.telefone().replaceAll("\\D", ""))
                .build();
    }

    public static EstabelecimentoDto criarEstabelecimentoDto(Estabelecimento estabelecimento){
        return new EstabelecimentoDto(estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone().replaceAll("\\D", ""),
                estabelecimento.getEspecialidade());
    }
}
