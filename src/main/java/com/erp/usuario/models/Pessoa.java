package com.erp.usuario.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private char sexo;
    private Boolean ativo;

}
