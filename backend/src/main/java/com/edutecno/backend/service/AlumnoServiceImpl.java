package com.edutecno.backend.service;

import com.edutecno.backend.dto.AlumnoCreateRequest;
import com.edutecno.backend.model.Alumno;
import com.edutecno.backend.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    private AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public void save(AlumnoCreateRequest alumno) {

        Alumno newAlumno = new Alumno();
        newAlumno.setRut(alumno.rut());
        newAlumno.setNombre(alumno.nombre());
        newAlumno.setDireccion(alumno.direccion());

        alumnoRepository.save(newAlumno);
    }

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }



}
