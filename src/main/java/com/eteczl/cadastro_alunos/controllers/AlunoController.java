package com.eteczl.cadastro_alunos.controllers;

import com.eteczl.cadastro_alunos.models.Aluno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cadastro")
public class AlunoController {

    private static List<Aluno> alunos = new ArrayList<>();

    @GetMapping
    public List<Aluno> index() {
        return alunos;
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Object> show(@PathVariable int id) {
        for( Aluno aluno : alunos ) {
            if(aluno.getId() == id) {
                return ResponseEntity.ok().body(aluno);
            }
        }
        return ResponseEntity.badRequest().body("Aluno não encontrado");
    }

    @PostMapping
    public Aluno store(@RequestBody Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    @PutMapping("/aluno/{id}/edit")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Aluno alunoNovo) {
        for( Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                aluno.setNome(alunoNovo.getNome());
                aluno.setSobrenome(alunoNovo.getSobrenome());
                aluno.setIdade(alunoNovo.getIdade());
                return ResponseEntity.ok(alunoNovo);
            }
        }
        return ResponseEntity.status(404).body(Map.of("erro", "Aluno não encontrado"));
    }

    @DeleteMapping("/aluno/{id}/delete")
    public ResponseEntity<Object> destroy(@PathVariable int id) {
        for(Aluno aluno : alunos) {
            if(aluno.getId() == id) {
                alunos.remove(aluno);
                return ResponseEntity.ok().body(Map.of("message", "Aluno deletado com sucesso."));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("erro", "Aluno não encontrado"));
    }

}
