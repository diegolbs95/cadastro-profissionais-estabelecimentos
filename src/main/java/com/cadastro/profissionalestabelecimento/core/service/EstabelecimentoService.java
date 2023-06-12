package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoDto;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;

public interface EstabelecimentoService {

    EstabelecimentoDto cadastrarEstabelecimento(EstabelecimentoDto estabelecimentoDto) throws EstabelecimentoException;

    EstabelecimentoDto atualizarEstabelecimento (String cnpjEstabelecimento, EstabelecimentoDto estabelecimentoDto) throws EstabelecimentoException;

    void deletarEstabelecimento(String cnpjEstabelecimento) throws EstabelecimentoException;

    EstabelecimentoDto buscarEstabelecimentoPorCnpj(String cnpjEstabelecimento) throws EstabelecimentoException;

    Estabelecimento buscarEstabelecimentoOuFalhar(String cnpjEstabelecimento) throws EstabelecimentoException;
}
