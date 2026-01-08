package com.solution.picpay_problem.security.authentication;

import com.solution.picpay_problem.domain.user.UserEntity;
import com.solution.picpay_problem.domain.user.UserRepository;
import com.solution.picpay_problem.security.userDetails.UserDetailsIml;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFIlter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public TokenFIlter(TokenService tokenService, UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // verifica se o usuario tem algum token no hearder
        String token = getToken(request);
        if (token != null){
            // pega o email do usuario guardado no token
            String email = this.tokenService.getSubjectToken(token);

            UserEntity user = this.userRepository.findByEmail(email);

            UserDetailsIml userDetailsIml = new UserDetailsIml(user);

            // Encapsula os dados do usuario
            var authetication = new UsernamePasswordAuthenticationToken(userDetailsIml, null, userDetailsIml.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authetication);
        }


    }
    private String getToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) {
            return authorizationHeader.replace("Barrer ", "");
        }
        return null;
    }
}
