package com.edutecno.backend.repository;

import com.edutecno.backend.model.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Alumno findById(long id);
    Alumno findByRut(String rut);

    @Override
    Page<Alumno> findAll(Pageable pageable);
}
