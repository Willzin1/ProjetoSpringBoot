package com.eteczl.cadastro_alunos.controllers;

import com.eteczl.cadastro_alunos.models.Aluno;
import com.eteczl.cadastro_alunos.models.Professor;
import com.eteczl.cadastro_alunos.models.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cadastro")
public class AlunoController {

    private static List<User> pessoas = new ArrayList<>();

    @GetMapping("/alunos")
    public List<User> index() {
        return pessoas.stream()
                .filter(user -> user instanceof Aluno)
                .collect(Collectors.toList());
    }

    @PostMapping("/alunos")
    public Aluno store(@RequestBody @Valid Aluno aluno) {
        pessoas.add(aluno);
        return aluno;
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<Object> show(@PathVariable int alunoId) {
        for (User user : pessoas) {
            if (user instanceof Aluno) {
                Aluno aluno = (Aluno) user;
                if (aluno.getId() == alunoId) {
                    return ResponseEntity.ok().body(aluno);
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Aluno não encontrado"));
    }

    @PutMapping("/aluno/{id}/edit")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody @Valid Aluno alunoNovo) {
        for (User user : pessoas) {
            if (user instanceof Aluno) {
                Aluno aluno = (Aluno) user;
                if (aluno.getId() == id) {
                    if (aluno.getIdade() >= 18) {
                        aluno.setNome(alunoNovo.getNome());
                        aluno.setSobrenome(alunoNovo.getSobrenome());
                        aluno.setIdade(alunoNovo.getIdade());
                        return ResponseEntity.ok(aluno);
                    } else {
                        return ResponseEntity.badRequest().body(Map.of("error", "Somente alunos maiores de 18 anos podem alterar informações"));
                    }
                }
            }
        }
        return ResponseEntity.status(404).body(Map.of("erro", "Aluno não encontrado"));
    }

    @DeleteMapping("/aluno/{id}/delete")
    public ResponseEntity<Object> destroy(@PathVariable int id) {
        for (User user : pessoas) {
            if (user instanceof Aluno) {
                Aluno aluno = (Aluno) user;
                if (aluno.getId() == id) {
                    pessoas.remove(aluno);
                    return ResponseEntity.ok().body(Map.of("success", "Aluno deletado com sucesso!"));
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("erro", "Aluno não encontrado"));
    }
}
