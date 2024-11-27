package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.*;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private String token;
    private String auth;
    private String username;

    public ResponseEntity<AlumnoWrapper> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        ResponseEntity<AlumnoWrapper> alumnoWrapper = restTemplate.exchange("http://localhost:8080/api/v1/alumnos", HttpMethod.GET, httpEntity, AlumnoWrapper.class);
        return alumnoWrapper;
    }

    public ResponseEntity<UserDTO> signUp(UserRegisterDto userRegisterDto){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/auth";
        HttpEntity<UserRegisterDto> request = new HttpEntity<>(userRegisterDto);
        ResponseEntity<UserDTO> response = restTemplate.exchange(url, HttpMethod.POST,request, UserDTO.class);
        return response;
    }



    public LoginResponse login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/auth/signIn";

        LoginRequest loginRequest = new LoginRequest(username, password);
        try {
            // Intentar autenticarse
            String token = restTemplate.postForObject(url, loginRequest, String.class);
            return new LoginResponse(true, token, null);
        } catch (HttpClientErrorException ex) {
            // Manejo específico según el código de estado
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return new LoginResponse(false, null, "Username o password incorrectos");
            } else if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                return new LoginResponse(false, null, "No tienes permiso para acceder");
            } else {
                return new LoginResponse(false, null, "Error inesperado: " + ex.getStatusCode());
            }
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return new LoginResponse(false, null, "Error al intentar conectarse con el servidor");
        }
    }


//    public String login(String username, String password){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/api/v1/auth/signIn";
//
//        LoginRequest loginRequest = new LoginRequest(username, password);
//        token =  restTemplate.postForObject(url, loginRequest, String.class);
//        System.out.println(token);
////        Cookie cookie = new Cookie("Bearer ",token);
////        cookie.setH
//        return token;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
