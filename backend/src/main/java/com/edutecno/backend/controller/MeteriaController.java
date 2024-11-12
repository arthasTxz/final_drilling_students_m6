package com.edutecno.backend.controller;

import com.edutecno.backend.dto.MateriaCreateRequest;
import com.edutecno.backend.model.Materia;
import com.edutecno.backend.repository.MateriaRepository;
import com.edutecno.backend.service.MateriaService;
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
    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void save(@RequestBody MateriaCreateRequest materia) {
        materiaService.save(materia);
    }
}
