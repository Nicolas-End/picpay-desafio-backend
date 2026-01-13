package com.solution.picpay_problem.security.authentication;

import com.solution.picpay_problem.domain.user.UserEntity;
import com.solution.picpay_problem.security.config.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    public UserEntity get(){
        return (UserEntity) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    }

}
