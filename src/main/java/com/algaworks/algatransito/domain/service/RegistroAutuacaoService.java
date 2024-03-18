package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAutuacaoService {

    private final RegistroVeiculoService registroVeiculoService;

    public RegistroAutuacaoService(RegistroVeiculoService registroVeiculoService){
        this.registroVeiculoService = registroVeiculoService;
    }
    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);

    }
}
