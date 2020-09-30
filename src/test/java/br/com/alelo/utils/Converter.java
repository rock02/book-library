package br.com.alelo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

	public static String objectTo(Object objeto) {

        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
             body = mapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
             System.err.println(new StringBuilder("Message Error: <br/>").append(e.getMessage()).toString());
        }
        return body;
   }
}
