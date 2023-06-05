package com.cadastro.profissionalestabelecimento.infra.factory;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoResponseDto;
import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

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

    public static EstabelecimentoResponseDto criarEstabelecimentoResponse(Estabelecimento estabelecimento){
        return new EstabelecimentoResponseDto(estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone(),
                estabelecimento.getEspecialidade(),
                criarListProfissionaisDto(estabelecimento.getProfissionais()));
    }

    private static List<ProfissionalDto> criarListProfissionaisDto(List<Profissional> profissionais) {
        var listProfissionalDto = new ArrayList<ProfissionalDto>();

        for (Profissional profissional:profissionais) {
            listProfissionalDto.add(ProfissionalFactory.criarProfissionalDto(profissional));
        }
        return listProfissionalDto;
    }
}
