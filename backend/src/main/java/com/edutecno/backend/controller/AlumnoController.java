package com.edutecno.backend.controller;

import com.edutecno.backend.dto.*;
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
    ResponseEntity<?> findByAlumno(@PathVariable Long alumnoId){
        return ResponseEntity.ok().body(alumnoService.findById(alumnoId));
    }

    @GetMapping("/materias/{alumnoId}")
    ResponseEntity<MateriasByAlumno> findMateriasByAlumno(@PathVariable Long alumnoId){
        List<Materia> materias = materiaRepository.findByAlumnosId(alumnoId);
        MateriasByAlumno materiasByAlumno = new MateriasByAlumno(materias);
        return ResponseEntity.ok().body(materiasByAlumno);
    }

    @PostMapping("/materias")
    ResponseEntity<?> findByAlumnoId(@RequestBody AddMateriaToAlumno addMateriaToAlumno){
        AlumnoDto alumno = alumnoService.update(addMateriaToAlumno);
        return ResponseEntity.ok().body(alumno);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<String> save(@RequestBody AlumnoCreateRequest alumno){
        alumnoService.save(alumno);
        return ResponseEntity.ok("Todo bien");
    }
}
