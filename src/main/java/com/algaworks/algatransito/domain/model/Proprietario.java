package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proprietario { //@Table(name = "tb_proprietario"), não é obrigatório

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //Só usar o notblank não funciona, precisa adicionar no controller deve ser validado
    @Size(max = 60) //Permitindo o máximo de 60 caracteres
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "fone") //No banco está escrito como fone, assim nomeando com o @column
    private String telefone;

}