package com.solution.picpay_problem.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.solution.picpay_problem.domain.user.UserEntity;
import com.solution.picpay_problem.security.userDetails.UserDetailsIml;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    Algorithm algorithm = Algorithm.HMAC256("Es9a é a chAv3 secr3ta");
    public String createToken (UserDetailsIml user) throws JWTCreationException{

            return JWT.create()
                .withIssuer("Nicolas-End")
                .withSubject(user.getUsername())
                .sign(algorithm);


    }

    public String getSubjectToken (String token) throws JWTDecodeException{

            return JWT.require(algorithm)
                .withIssuer("Nicolas-End")
                .build()
                .verify(token)
                .getSubject();

    }
}

