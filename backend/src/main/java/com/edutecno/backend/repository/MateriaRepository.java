package com.edutecno.backend.repository;

import com.edutecno.backend.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findByAlumnoId(Long id);
}
