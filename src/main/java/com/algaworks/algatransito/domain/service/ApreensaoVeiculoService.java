package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApreensaoVeiculoService {

    private final RegistroVeiculoService registroVeiculoService;

    public ApreensaoVeiculoService(RegistroVeiculoService registroVeiculoService){
        this.registroVeiculoService = registroVeiculoService;
    }

    @Transactional
    public void apreender(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public  void removerApreensao(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.removerApreensao();

    }
}
