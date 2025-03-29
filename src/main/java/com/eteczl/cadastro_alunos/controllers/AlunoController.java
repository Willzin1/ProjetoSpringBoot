package com.eteczl.cadastro_alunos.controllers;

import com.eteczl.cadastro_alunos.models.Aluno;
import com.eteczl.cadastro_alunos.models.Professor;
import com.eteczl.cadastro_alunos.models.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cadastro")
public class AlunoController {

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
        // Buscar o aluno com o ID correspondente
        for (User user : users) {
            if (user instanceof Aluno) {
                Aluno aluno = (Aluno) user;


                if (aluno.getId() == alunoID) {
                    // Verificar a idade do aluno
                    if (aluno.getIdade() >= 18) {
                        // Atualizar os dados do aluno
                        aluno.setNome(alunoNovo.getNome());
                        aluno.setSobrenome(alunoNovo.getSobrenome());
                        aluno.setIdade(alunoNovo.getIdade());
                        // Retornar a resposta com o aluno atualizado
                        return ResponseEntity.ok(aluno);
                    } else {
                        // Se o aluno não tem idade suficiente
                        return ResponseEntity.badRequest().body(Map.of("error", "Somente alunos maiores de 18 anos podem alterar informações"));
                    }
                }
            }
        }
        // Caso o aluno não seja encontrado
        return ResponseEntity.badRequest().body(Map.of("error", "Aluno não encontrado"));
    }

    @GetMapping("/professores")
    public List<User> indexProfessores(){
        return users.stream().filter(user -> user instanceof Professor)
                .map(user -> (Professor) user)
                .collect(Collectors.toList());
    }

    @PostMapping("/professores")
    public User storeProfessores(@RequestBody @Valid Professor professor) {
        users.add(professor);
        return professor;
    }

}
