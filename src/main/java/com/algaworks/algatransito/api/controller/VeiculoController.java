package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId){
     return veiculoRepository.findById(veiculoId)
             .map(veiculo -> {
                 var veiculoModel = new VeiculoModel();
                 veiculoModel.setId(veiculo.getId());
                 veiculoModel.setNomeProprietario(veiculo.getProprietario().getNome());
                 veiculoModel.setMarca(veiculo.getMarca());
                 veiculoModel.setModelo(veiculo.getModelo());
                 veiculoModel.setNumeroPlaca(veiculo.getPlaca());
                 veiculoModel.setStatus(veiculo.getStatus());
                 veiculoModel.setDataCadastro(veiculo.getDataCadastro());
                 veiculoModel.setDataApreensao(veiculo.getDataApreensao());

                 return veiculoModel;
             })
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);
    }

}
