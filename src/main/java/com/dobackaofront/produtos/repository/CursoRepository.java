package com.dobackaofront.produtos.repository;

import com.dobackaofront.produtos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
