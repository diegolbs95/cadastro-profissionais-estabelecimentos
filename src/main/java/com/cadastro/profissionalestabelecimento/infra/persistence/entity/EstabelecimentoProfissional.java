package com.cadastro.profissionalestabelecimento.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Table(name = "tb_estabelecimento_profissional")
public class EstabelecimentoProfissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Embedded
    private EstabelecimentoProfissionalPk id = new EstabelecimentoProfissionalPk();

    public void setProfissional(Profissional profissional){
        id.setProfissional(profissional);
    }

    public void setEstabelecimento(Estabelecimento estabelecimento){
        id.setEstabelecimento(estabelecimento);
    }
}
