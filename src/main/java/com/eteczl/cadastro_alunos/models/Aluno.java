package com.eteczl.cadastro_alunos.models;

import jakarta.validation.constraints.NotNull;

public class Aluno {
    @NotNull(message = "id não pode ser nulo.")
    private Integer id;

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Sobrenome não pode ser nulo")
    private String sobrenome;

    @NotNull(message = "Idade não pode ser nulo")
    private Integer idade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
