package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {
    private  Long id;
    private ProrpeitarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
