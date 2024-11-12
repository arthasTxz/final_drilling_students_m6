package com.edutecno.backend.service;

import com.edutecno.backend.dto.MateriaCreateRequest;
import com.edutecno.backend.model.Alumno;
import com.edutecno.backend.model.Materia;
import com.edutecno.backend.repository.AlumnoRepository;
import com.edutecno.backend.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService {

    private MateriaRepository materiaRepository;

    private AlumnoRepository alumnoRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository, AlumnoRepository alumnoRepository) {
        this.materiaRepository = materiaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public void save(MateriaCreateRequest materiaRequest) {
        Alumno alumno = alumnoRepository.findById(materiaRequest.alumnoId()).orElseThrow(() -> new RuntimeException("Alumno not found"));
        Materia materia = new Materia();
        materia.setAlumno(alumno);
        materia.setNombre(materiaRequest.nombre());
        materiaRepository.save(materia);
//        alumno.getMateriaList().add(materia);
    }
}
