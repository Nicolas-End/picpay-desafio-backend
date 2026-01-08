package com.solution.picpay_problem.security.userDetails;

import com.solution.picpay_problem.domain.user.UserEntity;
import com.solution.picpay_problem.domain.user.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceIml implements UserDetailsService {

    private UserRepository userRepository;
    public UserDetailsServiceIml(UserRepository userRepository){
        this.userRepository = userRepository;
    }




    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        /* Essa função é utilizada para identificar como o usuario sera achado no banco de dados
        * usando o Authentication Manager*/
        UserEntity user = userRepository.findByEmail(email);
        return new UserDetailsIml(user);

    }
}
