package com.eteczl.cadastro_alunos.controllers;

import com.eteczl.cadastro_alunos.models.Professor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cadastro")
public class ProfessorController {
    private static List<Professor> professores = new ArrayList<>();

    @GetMapping("/professores")
    public List<Professor> index(){
        return professores;
    }

    @PostMapping("/professores")
    public Professor store(@RequestBody @Valid Professor professor) {
        professores.add(professor);
        return professor;
    }

    @GetMapping("/professores/{professorID}")
    public ResponseEntity<Object> show(@PathVariable int professorID) {
        for ( Professor professor : professores) {
            if(professor.getId() == professorID) {
                return ResponseEntity.ok().body(professor);
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }

    @PutMapping("/professores/{professorID}/edit")
    public ResponseEntity<Object> update(@PathVariable int professorID, @RequestBody Professor professorNovo) {
        for (Professor professor : professores) {
            if(professor.getId() == professorID) {
                professor.setNome(professorNovo.getNome());
                professor.setSobrenome(professorNovo.getSobrenome());
                professor.setIdade(professorNovo.getIdade());
                professor.setCpf(professorNovo.getCpf());
                return ResponseEntity.ok().body(professor);
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }

    @DeleteMapping("/professores/{professorID}/delete")
    public ResponseEntity<Object> delete(@PathVariable int professorID) {
        for (Professor professor : professores) {
            if (professor.getId() == professorID) {
                professores.remove(professor);
                return ResponseEntity.ok().body(Map.of("message", "Aluno deletado com sucesso."));
            }
        }

        return ResponseEntity.ok().body(Map.of("message", "Aluno deletado com sucesso."));
    }
}
