package com.edutecno.backend.controller;

import com.edutecno.backend.dto.AlumnoCreateRequest;
import com.edutecno.backend.dto.AlumnoMateria;
import com.edutecno.backend.model.Alumno;
import com.edutecno.backend.model.Materia;
import com.edutecno.backend.repository.MateriaRepository;
import com.edutecno.backend.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final MateriaRepository materiaRepository;

    public AlumnoController(AlumnoService alumnoService, MateriaRepository materiaRepository) {
        this.alumnoService = alumnoService;
        this.materiaRepository = materiaRepository;
    }

    @GetMapping
    ResponseEntity<AlumnoMateria> findAll(){
        List<Alumno> alumnos = alumnoService.findAll();
        AlumnoMateria alumnoMateria = new AlumnoMateria(alumnos);
        return ResponseEntity.ok().body(alumnoMateria);
    }

    @GetMapping("/{alumnoId}")
    ResponseEntity<List<Materia>> findByAlumno(@PathVariable Long alumnoId){
        List<Materia> materias = materiaRepository.findByAlumnoId(alumnoId);
        return ResponseEntity.ok().body(materias);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<String> save(@RequestBody AlumnoCreateRequest alumno){
        alumnoService.save(alumno);
        return ResponseEntity.ok("Todo bien");
    }
}
