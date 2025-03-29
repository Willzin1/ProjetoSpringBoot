package com.eteczl.cadastro_alunos.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Aluno extends User {
    @NotNull(message = "id não pode ser nulo.")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
