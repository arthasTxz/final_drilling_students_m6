package com.edutecno.frontend.controller;

import com.edutecno.frontend.dto.*;
import com.edutecno.frontend.service.JwtUtils;
import com.edutecno.frontend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public LoginController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("token") String token, Model model) {
//        String token = (String) model.getAttribute("token");
        ResponseEntity<AlumnoWrapper> alumnoWrapper = userService.findAll(token);
        model.addAttribute("alumnos", alumnoWrapper.getBody().alumnos());
        System.out.println("Desde home: " + token);
        return "home";
    }


    @GetMapping("/signIn")
    public String login(Model model) throws JsonProcessingException {
        jwtUtils.claims("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9BRE1JTiIsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczMTM2OTQzOH0.I0yVEunVYzklejSKa0kwc5d4hHZARXOWnEFgJ3Khp2w");
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/signIn")
    public String registrarse(RedirectAttributes redirectAttributes, @ModelAttribute("loginRequest") LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String token = userService.login(username, password);

        System.out.println(token);
        redirectAttributes.addFlashAttribute("token", token);
        System.out.println(username);
        System.out.println(password);
        return "redirect:/home";
    }

//    @GetMapping("/alumnos")
//    public String alumnos(ModelMap model) {
//        AlumnoListDto alumnos = userService.findAll();
//        model.addAttribute("alumnos", alumnos);
//        return "alumnos";
//    }
}
