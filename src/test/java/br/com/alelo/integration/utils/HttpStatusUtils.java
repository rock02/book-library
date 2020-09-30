package br.com.alelo.integration.utils;

import org.springframework.http.HttpStatus;

public class HttpStatusUtils {

    public static HttpStatus returnedCodeStatus( String statusName ) {

       return HttpStatus.valueOf( statusName );

    }
}
