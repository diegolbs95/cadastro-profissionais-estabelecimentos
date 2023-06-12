package com.cadastro.profissionalestabelecimento.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Embeddable
public class EstabelecimentoProfissionalPk {

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
}
