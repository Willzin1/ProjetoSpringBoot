package com.eteczl.cadastro_alunos.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class Professor extends User {

    @NotNull(message = "CPF não pode ser nulo")
    @NotBlank(message = "CPF não pode ficar em branco")
    @CPF(message = "CPF Inválido")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
