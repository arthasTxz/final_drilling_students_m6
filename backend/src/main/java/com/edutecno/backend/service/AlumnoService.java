package com.edutecno.backend.service;

import com.edutecno.backend.dto.AddMateriaToAlumno;
import com.edutecno.backend.dto.AlumnoCreateRequest;
import com.edutecno.backend.dto.AlumnoDto;
import com.edutecno.backend.model.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlumnoService {

    void save(AlumnoCreateRequest alumno);
    List<Alumno> findAll();
    Page<Alumno> findAll(Pageable pageable);
    AlumnoDto findById(Long id);
    AlumnoDto update(AddMateriaToAlumno alummnoMateria);

}
