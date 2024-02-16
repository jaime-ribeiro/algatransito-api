package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name = "tb_proprietario")
public class Proprietario {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Este @Column não é obrigatório
    @Column
    private String nome;
    private String email;
    //No banco está escrito como fone, assim nomeando com o @column
    @Column(name = "fone")
    private String telefone;

}