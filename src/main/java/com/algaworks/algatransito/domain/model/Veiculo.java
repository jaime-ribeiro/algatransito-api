package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
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

    @ManyToOne
    //@JoinColumn(name = "proprietario_id") -não precisamos disso pq o jakarta persistence identifica automaticamente
    //Caso o nome da coluna não tivesse ****_id no nome, aí seria recomendado usar o join column especificando o nome
    private Proprietario proprietario;

    private String marca;
    private String modelo;
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
