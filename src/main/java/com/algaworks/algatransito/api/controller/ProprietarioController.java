package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioController(ProprietarioRepository proprietarioRepository){
        this.proprietarioRepository = proprietarioRepository;
    }
    @GetMapping("/proprietarios")
    public List<Proprietario> listar(){
        return proprietarioRepository.findAll();
        //return proprietarioRepository.findByNomeContaining("jai");
    }
}
