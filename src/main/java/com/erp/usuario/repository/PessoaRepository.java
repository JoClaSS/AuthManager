package com.erp.usuario.repository;

import com.erp.usuario.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Pessoa findByNome(String nome);
}
