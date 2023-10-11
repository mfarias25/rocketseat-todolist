package com.mfarias25.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component // toda class que queremos que o spring gerencie
public class FilterTaskAuth extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var authorization = request.getHeader("Authorization");
        var authEncoded = authorization.substring("Basic".length()).trim(); //subtring = int unico ou dois ints. Pege Basica e calcule qual o length, tira os caracteres e espaços

        byte[] authDecoded = Base64.getDecoder().decode(authEncoded); //Decode converter/converter um array de bytes

        var authString = new String(authDecoded);




        // 0 -> ["farias3"], 1 -> ["999333222"]
        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];
        System.out.println("Authorization: " +"Username: "+ username + " " + "Password: " + password);

        //Pegar a autenticação(usuario e senha)
        //Validar usuario
        //Validar senha
        //Tudo ok, segue!

        filterChain.doFilter(request, response);

    }
}
