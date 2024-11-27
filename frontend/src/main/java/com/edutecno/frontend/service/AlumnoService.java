package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class AlumnoService {

    public ResponseEntity<MateriasWrapper> findAllMateriasByAlumnoId(String token, Long alumnoId){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        ResponseEntity<MateriasWrapper> materias = restTemplate.exchange("http://localhost:8080/api/v1/alumnos/materias/" + alumnoId, HttpMethod.GET, httpEntity, MateriasWrapper.class);
        return materias;
    }

    public ResponseEntity<String> createAlumno(String token, AlumnoRegisterDto alumnoRegisterDto){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = "http://localhost:8080/api/v1/alumnos";
        HttpEntity<AlumnoRegisterDto> request = new HttpEntity<>(alumnoRegisterDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return response;
    }

    public ResponseEntity<AlumnoDTO> findAlumnoById(String token, Long alumnoId){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        ResponseEntity<AlumnoDTO> response = restTemplate.exchange("http://localhost:8080/api/v1/alumnos/" + alumnoId, HttpMethod.GET, httpEntity,AlumnoDTO.class);
        return response;
    }

    public ResponseEntity<?> addMateriaToAlumno(String token, AddMateriaToAlumno addMateriaToAlumno){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = "http://localhost:8080/api/v1/alumnos/materias";
        HttpEntity<AddMateriaToAlumno> request = new HttpEntity<>(addMateriaToAlumno, headers);
        ResponseEntity<AlumnoDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, AlumnoDTO.class);
        return response;
    }
}
