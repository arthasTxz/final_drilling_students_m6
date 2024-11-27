package com.edutecno.backend.service;

import com.edutecno.backend.dto.MateriaCreateRequest;
import com.edutecno.backend.model.Materia;
import org.springframework.stereotype.Service;


public interface MateriaService {

    Materia save(MateriaCreateRequest materiaRequest);
}
