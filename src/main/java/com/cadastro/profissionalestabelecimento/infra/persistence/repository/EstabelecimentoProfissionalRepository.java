package com.cadastro.profissionalestabelecimento.infra.persistence.repository;

import com.cadastro.profissionalestabelecimento.infra.persistence.entity.EstabelecimentoProfissional;
import com.cadastro.profissionalestabelecimento.infra.persistence.entity.EstabelecimentoProfissionalPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoProfissionalRepository extends JpaRepository<EstabelecimentoProfissional, EstabelecimentoProfissionalPk> {

}
