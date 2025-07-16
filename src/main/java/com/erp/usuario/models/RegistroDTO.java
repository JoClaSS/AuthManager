package com.erp.usuario.models;



public record RegistroDTO(String login, String password, UserRole role, PessoaDTO pessoa) {
}