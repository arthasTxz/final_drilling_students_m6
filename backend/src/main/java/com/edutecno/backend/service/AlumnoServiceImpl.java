package com.edutecno.backend.service;

import com.edutecno.backend.dto.AddMateriaToAlumno;
import com.edutecno.backend.dto.AlumnoCreateRequest;
import com.edutecno.backend.dto.AlumnoDto;
import com.edutecno.backend.exception.MateriaYaAsignada;
import com.edutecno.backend.model.Alumno;
import com.edutecno.backend.model.Materia;
import com.edutecno.backend.repository.AlumnoRepository;
import com.edutecno.backend.repository.MateriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    private AlumnoRepository alumnoRepository;

    private MateriaRepository materiaRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, MateriaRepository materiaRepository) {
        this.alumnoRepository = alumnoRepository;
        this.materiaRepository = materiaRepository;
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

    @Override
    public Page<Alumno> findAll(Pageable pageable) {
        return alumnoRepository.findAll(pageable);
    }

    @Override
    public AlumnoDto findById(Long id) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            return mappAlumnoDto(alumno);
        }
        return null;
    }

    @Override
    public AlumnoDto update(AddMateriaToAlumno alummnoMateria) {
        Alumno alumno = alumnoRepository.findById(alummnoMateria.alumnoId()).orElse(null);
        Materia materia = materiaRepository.findById(alummnoMateria.materiaId()).orElse(null);
        assert alumno != null;
        if (alumno.getMateriaList().contains(materia)){
            throw new MateriaYaAsignada("La materia ya ha sido asignada al alumno");
        }
        alumno.getMateriaList().add(materia);
        assert materia != null;
        materia.getAlumnos().add(alumno);
        materiaRepository.save(materia);
        return mappAlumnoDto(alumnoRepository.save(alumno));
    }

    private AlumnoDto mappAlumnoDto(Alumno alumno) {
        return new AlumnoDto(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getDireccion(),
                alumno.getMateriaList()
        );
    }
}
