package com.eteczl.cadastro_alunos.controllers;

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
public class ProfessorController {

    private static List<User> pessoas = new ArrayList<>();

    @GetMapping("/professores")
    public List<User> index() {
        return pessoas.stream()
                .filter(user -> user instanceof Professor)
                .collect(Collectors.toList());
    }

    @PostMapping("/professores")
    public Professor store(@RequestBody @Valid Professor professor) {
        pessoas.add(professor);
        return professor;
    }

    @GetMapping("/professores/{professorID}")
    public ResponseEntity<Object> show(@PathVariable int professorID) {
        for (User user : pessoas) {
            if (user instanceof Professor) {
                Professor professor = (Professor) user;
                if (professor.getId() == professorID) {
                    return ResponseEntity.ok().body(professor);
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }

    @PutMapping("/professores/{professorID}/edit")
    public ResponseEntity<Object> update(@PathVariable int professorID, @RequestBody Professor professorNovo) {
        for (User user : pessoas) {
            if (user instanceof Professor) {
                Professor professor = (Professor) user;
                if (professor.getId() == professorID) {
                    professor.setNome(professorNovo.getNome());
                    professor.setSobrenome(professorNovo.getSobrenome());
                    professor.setIdade(professorNovo.getIdade());
                    professor.setCpf(professorNovo.getCpf());
                    return ResponseEntity.ok().body(professor);
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }

    @DeleteMapping("/professores/{professorID}/delete")
    public ResponseEntity<Object> delete(@PathVariable int professorID) {
        for (User user : pessoas) {
            if (user instanceof Professor) {
                Professor professor = (Professor) user;
                if (professor.getId() == professorID) {
                    pessoas.remove(professor);
                    return ResponseEntity.ok().body(Map.of("message", "Professor deletado com sucesso."));
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }
}
