package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.AlumnoDTO;
import com.edutecno.frontend.dto.AlumnoListDto;
import com.edutecno.frontend.dto.AlumnoWrapper;
import com.edutecno.frontend.dto.LoginRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    public ResponseEntity<AlumnoWrapper> findAll(String token){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        System.out.println(httpEntity);
        ResponseEntity<AlumnoWrapper> alumnoWrapper = restTemplate.exchange("http://localhost:8080/api/v1/alumnos", HttpMethod.GET, httpEntity, AlumnoWrapper.class);
        System.out.println(alumnoWrapper);
        return alumnoWrapper;
    }

    public String login(String username, String password){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/auth/signIn";

        LoginRequest loginRequest = new LoginRequest(username, password);
        return restTemplate.postForObject(url, loginRequest, String.class);
    }


}
