package com.dobackaofront.produtos.controller;

import com.dobackaofront.produtos.entity.Curso;
import com.dobackaofront.produtos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> imprimirCursos() {
        List<Curso> cursos = cursoService.imprimirCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id){
        Optional<Curso> curso =  cursoService.findById(id);
        return curso.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> salvarNovoCurso(@RequestBody Curso curso){
        Curso novoCurso = cursoService.salvarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizaCurso(@RequestBody Curso cursoAtualizado,
                                               @PathVariable Long id){
        Optional<Curso> curso = cursoService.findById(id);

        if(curso.isPresent()){
            cursoAtualizado.setId(id);
            Curso cursoNovoSalvo = cursoService.salvarCurso(cursoAtualizado);
            return ResponseEntity.ok(cursoAtualizado);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.findById(id);

        if (curso.isPresent()) {
            cursoService.excluirCurso(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
