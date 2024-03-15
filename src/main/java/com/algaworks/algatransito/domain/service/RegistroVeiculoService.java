package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class RegistroVeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public RegistroVeiculoService(VeiculoRepository veiculoRepository, RegistroProprietarioService registroProprietarioService){
        this.veiculoRepository = veiculoRepository;
        this.registroProprietarioService = registroProprietarioService;
    }
    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if (novoVeiculo.getId()!=null){
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um codigo");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                        .isPresent();

        if(placaEmUso){
            throw new NegocioException("Já existe um veículo cadastrado com essa placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }
}