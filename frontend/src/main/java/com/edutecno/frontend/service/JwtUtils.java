package com.edutecno.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    public Map<String,String> claims(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String body = new String(decoder.decode(chunks[1]));
        Map<String,String> claims = new HashMap<>();
        Map json = stringToMap(body);
        System.out.println(json.get("role"));
        claims.put("token",header);
        claims.put("body",body);
        claims.put("username", (String) json.get("username"));
        claims.put("role", (String) json.get("role"));
        return claims;
    }

    private Map stringToMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = (ObjectNode) mapper.readTree(json);
        return mapper.convertValue(root, Map.class);
    }


}
