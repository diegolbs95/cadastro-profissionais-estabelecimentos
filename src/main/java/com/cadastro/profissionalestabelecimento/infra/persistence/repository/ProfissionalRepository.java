package com.cadastro.profissionalestabelecimento.infra.persistence.repository;

import com.cadastro.profissionalestabelecimento.infra.persistence.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional,Long> {

    Optional<Profissional> findByCpf(String cpfProfissional);
}
