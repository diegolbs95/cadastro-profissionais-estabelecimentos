package com.cadastro.profissionalestabelecimento.core.service;

import com.cadastro.profissionalestabelecimento.core.dto.EstabelecimentoProfissionalDto;
import com.cadastro.profissionalestabelecimento.infra.exception.EstabelecimentoException;
import com.cadastro.profissionalestabelecimento.infra.exception.ProfissionalException;

public interface CadastrarProfissionalNoEstabelecimentoService {

    String cadastrarProfissionalEstabelecimento(EstabelecimentoProfissionalDto estabelecimentoProfissionalDto)
                                                    throws EstabelecimentoException, ProfissionalException;
}
