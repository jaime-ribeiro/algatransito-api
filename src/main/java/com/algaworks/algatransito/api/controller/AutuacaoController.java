package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoAssembler autuacaoAssembler;

    private final RegistroVeiculoService registroVeiculoService;

    public AutuacaoController(RegistroAutuacaoService registroAutuacaoService, AutuacaoAssembler autuacaoAssembler, RegistroVeiculoService registroVeiculoService){
        this.registroAutuacaoService = registroAutuacaoService;
        this.autuacaoAssembler=autuacaoAssembler;
        this.registroVeiculoService=registroVeiculoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput){
        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService
                .registrar(veiculoId, novaAutuacao);

        return autuacaoAssembler.toModel(autuacaoRegistrada);
    }
    @GetMapping
    public List<AutuacaoModel> listar (@PathVariable Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return  autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
    }

}
