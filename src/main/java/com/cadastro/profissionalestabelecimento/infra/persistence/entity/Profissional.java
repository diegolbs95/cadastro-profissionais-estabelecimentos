package com.cadastro.profissionalestabelecimento.infra.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @CPF
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    private String endereco;
    private String telefoneCelular;
    private String telefoneFixo;
    private String especialidade;
    @Column(length = 10000)
    private String urlFoto;
}
