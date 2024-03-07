package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Long id;


    //@JoinColumn(name = "proprietario_id") -não precisamos disso pq o jakarta persistence identifica automaticamente
    //Caso o nome da coluna não tivesse ****_id no nome, aí seria recomendado usar o join column especificando o nome

    @Valid //Passa o valid pra ele obedecer a validação em cascata que será realizada de Veículo para Proprietário
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @ManyToOne
    @NotNull //não colocamos not blank pq não é uma String, só queremos garantir que tenha um objeto atribuído
    private Proprietario proprietario;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") //Podemos usar esta annotation pra passar um regex, padrão das placas: XXX0000 OR XXX0X00
    private String placa;

    //caso você import somente até JsonProperty você precisa colocar assim com "JsonProperty.Access.READ_ONLY"
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    //caso o seu import seja JsonProperty.Access você poderá botar só Access.READ_ONLY que funcionará
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCadastro; //o jakarta configura mesmo que tenha _ no nome da coluna, ele identifica

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;

}
