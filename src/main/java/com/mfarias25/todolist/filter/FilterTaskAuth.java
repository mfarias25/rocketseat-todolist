package com.mfarias25.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mfarias25.todolist.user.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component // toda class que queremos que o spring gerencie
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();
        if (servletPath.equals("/tasks/")) {
            //Pegar a autenticação(usuario e senha)
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim(); //subtring = int unico ou dois ints. Pege Basica e calcule qual o length, tira os caracteres e espaços

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded); //Decode converter/converter um array de bytes

            var authString = new String(authDecoded);

            // 0 -> ["farias3"], 1 -> ["999333222"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            //System.out.println("Authorization: " +"Username: "+ username + " " + "Password: " + password);

            //Validar usuario
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {

                //Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                //Tudo ok, segue!
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
