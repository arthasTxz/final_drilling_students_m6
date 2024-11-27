package com.edutecno.frontend.controller;

import com.edutecno.frontend.dto.*;
import com.edutecno.frontend.service.AlumnoService;
import com.edutecno.frontend.service.MateriaService;
import com.edutecno.frontend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alumnos")
public class AlumnosController {

    private final UserService userService;
    private final AlumnoService alumnoService;
    private final MateriaService materiaService;

    public AlumnosController(UserService userService, AlumnoService alumnoService, MateriaService materiaService) {
        this.userService = userService;
        this.alumnoService = alumnoService;
        this.materiaService = materiaService;
    }

    @GetMapping("/create")
    public String addAlumno(Model model){
        AlumnoRegisterDto alumno = new AlumnoRegisterDto();
        model.addAttribute("alumno", alumno);
        return "addAlumno";
    }

    @PostMapping("/create")
    public String addAlumno(@ModelAttribute AlumnoRegisterDto alumno, Model model){
        ResponseEntity<String> alumnos = alumnoService.createAlumno(userService.getToken(),alumno);
        if (alumnos.getStatusCode() == HttpStatus.CREATED){
           return "redirect:/home";
        }
        return "redirect:/home";
    }


    @GetMapping("/materias/{alumnoId}")
    public String addMateriaToAlumno(@PathVariable Long alumnoId, Model model){
        ResponseEntity<MateriasWrapper> response = materiaService.findAllMaterias(userService.getToken());
        List<MateriaDTO> materias = response.getBody().materias();
        ResponseEntity<AlumnoDTO> alumnoResponseEntity = alumnoService.findAlumnoById(userService.getToken(), alumnoId);
        model.addAttribute("alumno", alumnoResponseEntity.getBody());
        model.addAttribute("materias", materias);
        AddMateriaToAlumno addMateriaToAlumno = new AddMateriaToAlumno();
        addMateriaToAlumno.setAlumnoId(alumnoId);
        model.addAttribute("addMateriaToAlumno", addMateriaToAlumno);
        return "addMateriaToAlumno";
    }

    @PostMapping("/materias")
    public String addMateriaToAlumno(@ModelAttribute AddMateriaToAlumno addMateriaToAlumno){

        ResponseEntity<?> alumnoDTO = alumnoService.addMateriaToAlumno(userService.getToken(), addMateriaToAlumno);
        return "redirect:/home";
    }
}
