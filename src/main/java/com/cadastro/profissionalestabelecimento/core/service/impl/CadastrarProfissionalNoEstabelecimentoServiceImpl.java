package com.cadastro.profissionalestabelecimento.core.service.impl;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.CadastrarProfissionalNoEstabelecimentoService;
import com.cadastro.profissionalestabelecimento.core.service.EstabelecimentoService;
import com.cadastro.profissionalestabelecimento.core.service.ProfissionalService;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.EstabelecimentoProfissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.EstabelecimentoProfissionalRepository;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarProfissionalNoEstabelecimentoServiceImpl implements CadastrarProfissionalNoEstabelecimentoService {

    private final EstabelecimentoService estabelecimentoService;

    private final ProfissionalService profissionalService;

    private final EstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;

    @Override
    public String cadastrarProfissionalEstabelecimento(EstabelecimentoProfissionalDto estabelecimentoProfissionalDto)
                                                        throws EstabelecimentoException, ProfissionalException {

        log.info("Api cadastro de profissionais no estabelecimento.");
        var estabelecimento = estabelecimentoService
                .buscarEstabelecimentoOuFalhar(estabelecimentoProfissionalDto.cnpjEstabelecimento());

        var profissional = profissionalService
                .buscarProfissionalOuFalhar(estabelecimentoProfissionalDto.cpfProfissional());

        validarProfissionalEspecialidade(estabelecimento, profissional);

        var estabelecimentoProfissional = new EstabelecimentoProfissional();
        estabelecimentoProfissional.setEstabelecimento(estabelecimento);
        estabelecimentoProfissional.setProfissional(profissional);

        estabelecimentoProfissionalRepository.save(estabelecimentoProfissional);
        log.info("Profissional cadastrado no estabelecimento.");

        return "PROFISSIONAL_CADASTRADO_NO_ESTABELECIMENTO";
    }

    private void validarProfissionalEspecialidade(Estabelecimento estabelecimento, Profissional profissional)
                                                                                throws EstabelecimentoException {

        var listProfissionalEstabelecimento = estabelecimentoProfissionalRepository
                                                                        .findAllByIdProfissionalId(profissional.getId());

        for (EstabelecimentoProfissional estabelecimentoProfissional:listProfissionalEstabelecimento){
            if (estabelecimentoProfissional.getId().getEstabelecimento().getId().equals(estabelecimento.getId()) ||
                    estabelecimentoProfissional.getId().getEstabelecimento().getEspecialidade()
                            .equalsIgnoreCase(estabelecimento.getEspecialidade())) {

                log.error("Estabelecimento fora das regras para cadastramento");
                throw new EstabelecimentoException("Estabelecimento fora das regras para cadastramento");
            }
        }
    }
}
