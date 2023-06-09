package com.cadastro.profissionalestabelecimento.infra.persistence.repository;

import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {

    Optional<Estabelecimento> findByCnpj(String cnpj);
}