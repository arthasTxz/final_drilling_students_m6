package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.MateriaCreateDto;
import com.edutecno.frontend.dto.MateriaDTO;
import com.edutecno.frontend.dto.MateriasWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MateriaService {

    public ResponseEntity<MateriasWrapper> findAllMaterias(String token){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<MateriasWrapper> materias = restTemplate.exchange("http://localhost:8080/api/v1/materias", HttpMethod.GET, httpEntity, MateriasWrapper.class);
        return materias;
    }

    public ResponseEntity<MateriaDTO> createMateria(String token, MateriaCreateDto materia){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = "http://localhost:8080/api/v1/materias";
        HttpEntity<MateriaCreateDto> request = new HttpEntity<>(materia, headers);
        ResponseEntity<MateriaDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, MateriaDTO.class);
        return response;
    }
}
