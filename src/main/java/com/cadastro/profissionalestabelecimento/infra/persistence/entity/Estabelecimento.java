package com.cadastro.profissionalestabelecimento.infra.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @CNPJ
    @NotBlank(message = "CNPJ é obrigatorio")
    private String cnpj;
    private String endereco;
    private String telefone;
    private String especialidade;
}
