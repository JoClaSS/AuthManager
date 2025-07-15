package com.erp.usuario.service;

import com.erp.usuario.models.Pessoa;
import com.erp.usuario.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pRepository.saveAndFlush(pessoa);
    }

    public Pessoa getLastPessoa(){
        List<Pessoa> pessoaList = pRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return pessoaList.get(pessoaList.size()-1);
    }

    public ResponseEntity<Pessoa> findByName(String nome) {
        Pessoa pessoa = pRepository.findByNome(nome);
        return ResponseEntity.ok(pessoa);
    }
}
