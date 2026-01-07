package com.solution.picpay_problem.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.solution.picpay_problem.domain.user.UserEntity;

public class JwtAuthentication {
    Algorithm algorithm = Algorithm.HMAC256("Es9a é a chAv3 secr3ta");
    public String createToken (UserEntity user){
        try{

            return JWT.create()
                .withIssuer("Nicolas-End")
                .withSubject(user.getEmail())
                .sign(algorithm);


        }catch (JWTCreationException error){
            throw error;
        }
    }

    public String getSubjectToken (String token){
        try{

            return JWT.require(algorithm)
                .withIssuer("Nicolas-End")
                .build()
                .verify(token)
                .getSubject();

        }catch (JWTDecodeException error){
            throw error;
        }
    }
}

