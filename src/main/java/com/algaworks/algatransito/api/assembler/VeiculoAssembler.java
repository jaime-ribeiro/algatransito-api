package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VeiculoAssembler {

    public VeiculoAssembler(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    private final ModelMapper modelMapper;

    public VeiculoModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoModel.class);
    }

    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos){
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }

}
