package com.cadastro.profissionalestabelecimento.core.service.impl;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoResponseDto;
import com.cadastro.profissionalestabelecimento.core.service.EstabelecimentoService;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import com.cadastro.profissionalestabelecimento.infra.factory.EstabelecimentoFactory;
import com.cadastro.profissionalestabelecimento.infra.persistence.repository.EstabelecimentoRepository;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public EstabelecimentoDto cadastrarEstabelecimento(EstabelecimentoDto estabelecimentoDto)
                                                                    throws EstabelecimentoException {

        log.info("Acionando api cadastramento de Estabelecimento");
        return validarExisteEstabelecimentoJaCadastrado(EstabelecimentoFactory
                .criarEstabelecimento(estabelecimentoDto));

    }

    @Override
    public EstabelecimentoDto atualizarEstabelecimento(String cnpj, EstabelecimentoDto estabelecimentoDto)
                                                                            throws EstabelecimentoException {

        log.info(String.format("Atualizacao de dados para estabelecimento com CNPJ: %s.", cnpj));
        var estabelecimentoAtual = buscarEstabelecimentoOuFalhar(cnpj);

        BeanUtils.copyProperties(estabelecimentoDto, estabelecimentoAtual, "id", "cnpj");
        estabelecimentoRepository.save(estabelecimentoAtual);
        log.info(String.format("Solicitacao de atualizacao de dados para estabelecimento com CNPJ: " +
                "%s, concluida.", cnpj));

        return EstabelecimentoFactory.criarEstabelecimentoDto(estabelecimentoAtual);
    }

    @Override
    public void deletarEstabelecimento(String cnpj) throws EstabelecimentoException {

        log.info(String.format("Solicitacao de exclusao de estabelecimento com CNPJ: %s.", cnpj));
        var estabelecimento = buscarEstabelecimentoOuFalhar(cnpj);

        estabelecimentoRepository.delete(estabelecimento);
        log.info(String.format("Estabelecimento com CNPJ: %s excluido com sucesso.", cnpj));
    }

    @Override
    public EstabelecimentoResponseDto buscarEstabelecimentoPorCnpj(String cnpjEstabelecimento) throws EstabelecimentoException {
        log.info("Buscando estabelecimento com CNPJ: " + cnpjEstabelecimento);
        var estabelecimento = buscarEstabelecimentoOuFalhar(cnpjEstabelecimento);

        return EstabelecimentoFactory.criarEstabelecimentoResponse(estabelecimento);
    }

    @Override
    public Estabelecimento buscarEstabelecimentoOuFalhar(String cnpjEstabelecimento) throws EstabelecimentoException {
        var estabelecimentoAtual = estabelecimentoRepository
                                                      .findByCnpj(cnpjEstabelecimento.replaceAll("\\D", ""));
        if (estabelecimentoAtual.isEmpty()){
            log.error("[ERRO] - Estabelecimento nao encontrado com CNPJ: %s." + cnpjEstabelecimento);
            throw new EstabelecimentoException("Nao existem Estabelecimento com CNPJ solicitado.");
        }
        return estabelecimentoAtual.get();
    }

    private EstabelecimentoDto validarExisteEstabelecimentoJaCadastrado(Estabelecimento estabelecimento)
                                                                        throws EstabelecimentoException {
        var listEstabelecimentos = estabelecimentoRepository.findAll();
        estabelecimento.setCnpj(estabelecimento.getCnpj().replaceAll("\\D", ""));

        log.info("Validando se existe Estabelecimento com esse CNPJ");

        for (Estabelecimento estabelecimento1: listEstabelecimentos) {
            if (estabelecimento1.getCnpj().equals(estabelecimento.getCnpj())) {
                log.error("[ERRO] - Ja existe Estabelecimento com esse CNPJ");
                throw new EstabelecimentoException("Ja existe Estabelecimento com esse CNPJ");
            }
        }
        log.info("Salvando Estabelecimento");
        estabelecimentoRepository.save(estabelecimento);
        return EstabelecimentoFactory.criarEstabelecimentoDto(estabelecimento);
    }
}
