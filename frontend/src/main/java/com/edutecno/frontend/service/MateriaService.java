package com.edutecno.frontend.service;

import com.edutecno.frontend.dto.MateriaCreateDto;
import com.edutecno.frontend.dto.MateriaDTO;
import com.edutecno.frontend.dto.MateriasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MateriaService {

    @Value("${base.path.url}")
    private String basePath;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<MateriasWrapper> findAllMaterias(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<MateriasWrapper> materias = restTemplate.exchange( basePath+ "/materias", HttpMethod.GET, httpEntity, MateriasWrapper.class);
        return materias;
    }

    public ResponseEntity<MateriaDTO> createMateria(String token, MateriaCreateDto materia){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = basePath + "/materias";
        HttpEntity<MateriaCreateDto> request = new HttpEntity<>(materia, headers);
        ResponseEntity<MateriaDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, MateriaDTO.class);
        return response;
    }
}
