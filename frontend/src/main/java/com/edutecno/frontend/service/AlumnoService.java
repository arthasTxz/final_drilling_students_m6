package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class AlumnoService {

    @Value("${base.path.url}")
    private String basePath;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<MateriasWrapper> findAllMateriasByAlumnoId(String token, Long alumnoId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        ResponseEntity<MateriasWrapper> materias = restTemplate.exchange("http://backend:8080/api/v1/alumnos/materias/" + alumnoId, HttpMethod.GET, httpEntity, MateriasWrapper.class);
        return materias;
    }

    public ResponseEntity<String> createAlumno(String token, AlumnoRegisterDto alumnoRegisterDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = basePath + "/alumnos";
        HttpEntity<AlumnoRegisterDto> request = new HttpEntity<>(alumnoRegisterDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return response;
    }

    public ResponseEntity<AlumnoDTO> findAlumnoById(String token, Long alumnoId){
        HttpHeaders headers = new HttpHeaders();
        String url = basePath + "/alumnos/" + alumnoId;
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        ResponseEntity<AlumnoDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,AlumnoDTO.class);
        return response;
    }

    public ResponseEntity<?> addMateriaToAlumno(String token, AddMateriaToAlumno addMateriaToAlumno){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = basePath + "/alumnos/materias";
        System.out.println(url);
        HttpEntity<AddMateriaToAlumno> request = new HttpEntity<>(addMateriaToAlumno, headers);
        try{
            ResponseEntity<AlumnoDTO> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, AlumnoDTO.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La materia ya ha sido asignada.");
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud incorrecta.");
            } else {
                // Otros errores genéricos
                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        }  catch (Exception e) {
        // Manejo genérico de excepciones
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
    }

    }
}
