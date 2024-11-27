package com.edutecno.backend.controller;

import com.edutecno.backend.dto.MateriaCreateRequest;
import com.edutecno.backend.dto.MateriaWrapper;
import com.edutecno.backend.model.Materia;
import com.edutecno.backend.repository.MateriaRepository;
import com.edutecno.backend.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/materias")
public class MeteriaController {

    private final MateriaService materiaService;
    private final MateriaRepository materiaRepository;

    public MeteriaController(MateriaService materiaService, MateriaRepository materiaRepository) {
        this.materiaService = materiaService;
        this.materiaRepository = materiaRepository;
    }

    @GetMapping
    public ResponseEntity<MateriaWrapper> getAllMaterias() {
        List<Materia> materias = materiaRepository.findAll();
        return ResponseEntity.ok(new MateriaWrapper(materias));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Materia> save(@RequestBody MateriaCreateRequest materia) {
        Materia newMateria = materiaService.save(materia);
        return new ResponseEntity<>(newMateria, HttpStatus.CREATED);
    }
}
