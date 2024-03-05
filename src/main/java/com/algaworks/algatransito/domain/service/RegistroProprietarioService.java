package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {


    private final ProprietarioRepository proprietarioRepository;

    public RegistroProprietarioService(ProprietarioRepository proprietarioRepository){
        this.proprietarioRepository = proprietarioRepository;
    }

    //Esta annotation, se não conseguir salvar, ele dá um rollback
    @Transactional
    public Proprietario salvar(Proprietario proprietario){
        //Quando for pesquisado o e-mail ele vai olhar para o proprietário que não é igual ao proprietário informado
        //Ou seja, ele só vai pesquisar aqueles que são diferentes do e-mail, a pesquisa está sendo feita depois, e não um comparativo de e-mail.
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
        if(emailEmUso){
            throw new NegocioException("Já existe um proprietario cadastro com este e-mail");
        }
        return proprietarioRepository.save(proprietario);
    }

    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }
}
