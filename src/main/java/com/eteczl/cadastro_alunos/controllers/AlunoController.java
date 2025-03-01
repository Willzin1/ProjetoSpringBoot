package com.eteczl.cadastro_alunos.controllers;

import com.eteczl.cadastro_alunos.models.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class AlunoController {

    private static List<Aluno> alunos = new ArrayList<>();

    @GetMapping("/aluno")
    public List<Aluno> index() {
        return alunos;
    }

    @PostMapping("/aluno")
    public Aluno store(@RequestBody Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }
}
