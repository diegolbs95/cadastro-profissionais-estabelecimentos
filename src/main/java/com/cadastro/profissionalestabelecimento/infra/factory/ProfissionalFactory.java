package com.cadastro.profissionalestabelecimento.infra.factory;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfissionalFactory {

    public static Profissional criarProfissional(ProfissionalDto profissionalDto){
        return Profissional.builder()
                .id(profissionalDto.id())
                .nome(profissionalDto.nome())
                .endereco(profissionalDto.endereco())
                .cpf(profissionalDto.cpf())
                .especialidade(profissionalDto.especialidade())
                .telefoneCelular(profissionalDto.telefoneCelular().replaceAll("\\D", ""))
                .telefoneFixo(profissionalDto.telefoneFixo())
                .urlFoto(profissionalDto.urlFoto())
                .build();
    }

    public static ProfissionalDto criarProfissionalDto(Profissional profissional){
        return new ProfissionalDto(profissional.getId(),
                profissional.getNome(),
                profissional.getCpf(),
                profissional.getEndereco(),
                profissional.getTelefoneCelular(),
                profissional.getTelefoneFixo(),
                profissional.getEspecialidade(),
                profissional.getUrlFoto());
    }
}
