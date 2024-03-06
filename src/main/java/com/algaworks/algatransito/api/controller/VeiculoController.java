package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;

    public VeiculoController(VeiculoRepository veiculoRepository, RegistroVeiculoService registroVeiculoService){
        this.veiculoRepository = veiculoRepository;
        this.registroVeiculoService = registroVeiculoService;
    }

    @GetMapping
    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId){
     return veiculoRepository.findById(veiculoId)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);
    }
}
