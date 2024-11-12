package com.edutecno.backend.service;

import com.edutecno.backend.dto.AlumnoCreateRequest;
import com.edutecno.backend.model.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlumnoService {

    void save(AlumnoCreateRequest alumno);
    List<Alumno> findAll();

}
