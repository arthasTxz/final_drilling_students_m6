package com.edutecno.frontend.controller;

import com.edutecno.frontend.dto.MateriaCreateDto;
import com.edutecno.frontend.dto.MateriaDTO;
import com.edutecno.frontend.dto.MateriasWrapper;
import com.edutecno.frontend.service.JwtUtils;
import com.edutecno.frontend.service.MateriaService;
import com.edutecno.frontend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("materias")
public class MateriasController {

    private final MateriaService materiaService;
    private final UserService userService;

    public MateriasController(MateriaService materiaService, UserService userService) {
        this.materiaService = materiaService;
        this.userService = userService;
    }

    @GetMapping("/lista")
    public String getMaterias(Model model) {
        System.out.println("token desde materia " + userService.getToken());
        ResponseEntity<MateriasWrapper> materiasWrapper = materiaService.findAllMaterias(userService.getToken());
        System.out.println(materiasWrapper.getBody());
        model.addAttribute("materias", Objects.requireNonNull(materiasWrapper.getBody()).materias());
        return "listaMaterias";
    }

    @GetMapping("/create")
    public String addMaterias(Model model) {
        MateriaCreateDto materiaCreateDto = new MateriaCreateDto();
        model.addAttribute("materia", materiaCreateDto);

        return "addMateria";
    }

    @PostMapping()
    public String addMaterias(@ModelAttribute MateriaCreateDto materiaCreateDto) {
        ResponseEntity<MateriaDTO> response = materiaService.createMateria(userService.getToken(), materiaCreateDto);
        System.out.println(response.getBody());
        if (response.getStatusCode() == HttpStatus.CREATED){
            System.out.println("Entra created");
            return "redirect:materias/lista";
        }
        return "materias/create";
    }
}
