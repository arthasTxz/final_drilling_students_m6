package com.edutecno.frontend.controller;

import com.edutecno.frontend.dto.*;
import com.edutecno.frontend.service.AlumnoService;
import com.edutecno.frontend.service.JwtUtils;
import com.edutecno.frontend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;
    private final AlumnoService alumnoService;
    private final JwtUtils jwtUtils;

    public LoginController(UserService userService, JwtUtils jwtUtils, AlumnoService alumnoService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.alumnoService = alumnoService;
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("token") String token, Model model) throws JsonProcessingException {
//        String token = (String) model.getAttribute("token");
        if (userService.getToken() == null || userService.getToken().isEmpty()) {
            return "redirect:/signIn";
        }
        ResponseEntity<AlumnoWrapper> alumnoWrapper = userService.findAll();


        System.out.println("Desde controller: " + userService.getAuth());
        model.addAttribute("role", userService.getAuth());
        model.addAttribute("username", userService.getUsername());
        model.addAttribute("alumnos", alumnoWrapper.getBody().alumnos());
        System.out.println("Desde home: " + token);
        return "home";
    }

    @GetMapping("/test")
    public String test(){
        return "home";
    }


    @GetMapping("/signIn")
    public String login(Model model) throws JsonProcessingException {
        String error = (String) model.getAttribute("message");
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/signIn")
    public String registrarse(RedirectAttributes redirectAttributes, @ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) throws JsonProcessingException {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        LoginResponse response = userService.login(username, password);

        if( !response.success()){
//            model.addAttribute("errorMessage", response.message());
            redirectAttributes.addFlashAttribute("message", response.message());
            return "redirect:/signIn";
        }
        Map<String, String> claims = jwtUtils.claims(response.token());
        String auth = jwtUtils.claims(response.token()).get("role");
        String user = jwtUtils.claims(response.token()).get("username");
        userService.setAuth(auth);
        userService.setUsername(user);
//        System.out.println(token);
        redirectAttributes.addFlashAttribute("token", response.token());
        userService.setToken(response.token());
        return "redirect:/home";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userRegister", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/signUp")
    public String register(@ModelAttribute("userRegister") UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        ResponseEntity<UserDTO> response =userService.signUp(userRegisterDto);
        if (response.getStatusCode() == HttpStatus.CREATED){
            System.out.println("Entra created");
            return "redirect:/signIn";
        }
        return "signUp";
    }

    @GetMapping("/signOut")
    public String signOut(){
        userService.setAuth(null);
        userService.setToken(null);
        return "redirect:/signIn";
    }

    @GetMapping("alumnos/{alumnoId}")
    public String materiaDTOList(@PathVariable String alumnoId,Model model){
        ResponseEntity<MateriasWrapper> materiasWrapper = alumnoService.findAllMateriasByAlumnoId(userService.getToken(), Long.parseLong(alumnoId));
        List<MateriaDTO> materias = new ArrayList<>();
        if (materiasWrapper.hasBody()) {
            materias = materiasWrapper.getBody().materias();
        }
        model.addAttribute("materias", materias);
        return "materias";
    }

}
