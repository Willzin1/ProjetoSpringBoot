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
public class MainController {

    private static List<User> users = new ArrayList<>();

    @GetMapping("/alunos")
    public List<User> indexAlunos() {
        return users.stream().filter(user -> user instanceof Aluno)
                .map(user -> (Aluno) user)
                .collect(Collectors.toList());
    }

    @PostMapping("/alunos")
    public User storeAluno(@RequestBody Aluno aluno) {
        users.add(aluno);

        return aluno;
    }

    @PutMapping("/alunos/{alunoID}/edit")
    public ResponseEntity<Object> updateAluno(@PathVariable int alunoID, @RequestBody Aluno alunoNovo) {
        for (User user : users) {
            if (user instanceof Aluno) {
                Aluno aluno = (Aluno) user;

                if (aluno.getId() == alunoID) {

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
        return ResponseEntity.badRequest().body(Map.of("error", "Aluno não encontrado"));
    }

    @GetMapping("/professores")
    public List<User> indexProfessores(){
        return users.stream().filter(user -> user instanceof Professor)
                .map(user -> (Professor) user)
                .collect(Collectors.toList());
    }

    @PostMapping("/professores")
    public User storeProfessores(@RequestBody Professor professor) {
        users.add(professor);
        return professor;
    }

    @PutMapping("/professores/{professorID}/edit")
    public ResponseEntity<Object> updateProfessor(@PathVariable int professorID, @RequestBody Professor professorNovo) {
        for (User user : users) {
            if (user instanceof Professor) {
               Professor professor = (Professor) user;

                if (professor.getId() == professorID) {
                    professor.setNome(professorNovo.getNome());
                    professor.setSobrenome(professorNovo.getSobrenome());
                    professor.setIdade(professorNovo.getIdade());

                    return ResponseEntity.ok().body(professor);
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Professor não encontrado"));
    }


}
