package com.cadastro.profissionalestabelecimento.core.service.impl;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.core.service.ProfissionalService;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import com.cadastro.profissionalestabelecimento.infra.factory.ProfissionalFactory;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.ProfissionalRepository;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfissionalServiceImpl implements ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    @Override
    public ProfissionalDto cadastrarProfissional(ProfissionalDto profissionalDto)
                                                    throws ProfissionalException {

        log.info("Acionando api cadastramento de Profissional");
        return validarExisteProfissionalJaCadastrado(ProfissionalFactory
                .criarProfissional(profissionalDto));
    }

    @Override
    public ProfissionalDto atualizarProfissional(String cpfProfissional, ProfissionalDto profissionalDto)
                                                    throws ProfissionalException {

        log.info("Atualizacao de dados para profissional com CPF: " + cpfProfissional);
        var profissionalAtual = buscarProfissionalOuFalhar(cpfProfissional);

        BeanUtils.copyProperties(profissionalDto, profissionalAtual, "id", "cpf");
        profissionalRepository.save(profissionalAtual);
        log.info(String.format("Solicitacao de atualizacao de dados para profissional com CPF: " +
                "%s, concluida.", cpfProfissional));

        return ProfissionalFactory.criarProfissionalDto(profissionalAtual);
    }

    @Override
    public void excluirProfissional(String cpfProfissional) throws ProfissionalException {

        log.info("Solicitacao de exclusao de profissional com CPF: " + cpfProfissional);
        var profissional = buscarProfissionalOuFalhar(cpfProfissional);

        profissionalRepository.delete(profissional);
        log.info(String.format("Profissional com CPF: %s excluido com sucesso.", cpfProfissional));
    }

    @Override
    public ProfissionalDto buscarProfissionalPorCpf(String cpfProfissional) throws ProfissionalException {
        log.info("Buscando profissional com CPF: " + cpfProfissional);
        var profissional = buscarProfissionalOuFalhar(cpfProfissional);

        return ProfissionalFactory.criarProfissionalDto(profissional);
    }

    @Override
    public Profissional buscarProfissionalOuFalhar(String cpfProfissional) throws ProfissionalException {
        var profissionalAtual = profissionalRepository
                                                      .findByCpf(cpfProfissional.replaceAll("\\D", ""));
        if (profissionalAtual.isEmpty()){
            log.error("[ERRO] - Profissional nao encontrado com CPF: " + cpfProfissional);
            throw new ProfissionalException("Nao existem Profissional com CPF solicitado.");
        }
        return profissionalAtual.get();
    }

    private ProfissionalDto validarExisteProfissionalJaCadastrado(Profissional profissional) throws ProfissionalException {
        var listProfissionais = profissionalRepository.findAll();
        profissional.setCpf(profissional.getCpf().replaceAll("\\D", ""));

        log.info("Validando se existe Profissional com CPF.");

        for (Profissional profissional1: listProfissionais) {
            if (profissional1.getCpf().equals(profissional.getCpf())) {
                log.error("[ERRO] - Ja existe Profissional com esse CPF.");
                throw new ProfissionalException("Ja existe Profissional com esse CPF.");
            }
        }
        log.info("Salvando Profissional.");
        profissionalRepository.save(profissional);
        return ProfissionalFactory.criarProfissionalDto(profissional);
    }
}
