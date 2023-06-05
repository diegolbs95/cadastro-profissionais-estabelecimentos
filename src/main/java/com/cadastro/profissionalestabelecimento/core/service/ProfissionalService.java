package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.ProfissionalDto;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;

public interface ProfissionalService {

    ProfissionalDto cadastrarProfissional(ProfissionalDto profissionalDto) throws ProfissionalException;

    ProfissionalDto atualizarProfissional(String cpfProfissional,
                                          ProfissionalDto profissionalDto) throws ProfissionalException;

    void excluirProfissional(String cpfProfissional) throws ProfissionalException;

    ProfissionalDto buscarProfissionalPorCpf(String cpfProfissional) throws ProfissionalException;

    Profissional buscarProfissionalOuFalhar(String cpfProfissional) throws ProfissionalException;
}
