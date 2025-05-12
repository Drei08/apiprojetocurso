package com.dobackaofront.produtos.service;

import com.dobackaofront.produtos.entity.Curso;
import com.dobackaofront.produtos.repository.CursoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> imprimirCursos(){
        return cursoRepository.findAll();
    }

    public Curso salvarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Optional<Curso> findById(Long id){
        return cursoRepository.findById(id);
    }

    public void excluirCurso(Long id){
        cursoRepository.deleteById(id);
    }
}
