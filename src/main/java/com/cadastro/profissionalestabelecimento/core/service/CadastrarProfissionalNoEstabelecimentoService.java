package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.EstabelecimentoProfissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.EstabelecimentoProfissionalRepository;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.ProfissionalRepository;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarProfissionalNoEstabelecimentoService {

    private final EstabelecimentoService estabelecimentoService;

    private final ProfissionalRepository profissionalRepository;

    private final ProfissionalService profissionalService;

    private final EstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;

    public String cadastrarProfissionalEstabelecimento(EstabelecimentoProfissionalDto estabelecimentoProfissionalDto)
                                                                                throws EstabelecimentoException,
                                                                                ProfissionalException {

        log.info("Api de cadastro de profissionais no estabelecimento.");
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
        var listProfissionais = estabelecimento.getProfissionais();

        log.info("Validando se profissional esta dentro das normas exigidas.");
        for (Profissional profissional1:listProfissionais) {
            if (profissional1.getCpf().equals(profissional.getCpf()) ||
                profissional1.getEspecialidade().equalsIgnoreCase(profissional.getEspecialidade())){
                log.error("Profissional ja cadastrado ou estabelecimento não aceita mais essa especialidade.");
                throw new EstabelecimentoException("Profissional fora das regras para cadastramento");
            }
        }
        profissional.setEstabelecimento(estabelecimento);
        profissionalRepository.save(profissional);
    }
}
