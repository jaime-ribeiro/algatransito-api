package com.algaworks.algatransito.domain.model;

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
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;
    private LocalDateTime dataCadastro; //o jakarta configura mesmo que tenha _ no nome da coluna, ele identifica
    private LocalDateTime dataApreensao;

}
