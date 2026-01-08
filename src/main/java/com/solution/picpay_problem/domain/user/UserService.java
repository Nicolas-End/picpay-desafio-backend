package com.solution.picpay_problem.domain.user;

import com.solution.picpay_problem.dtos.token.TokenValueDTO;
import com.solution.picpay_problem.dtos.user.UserRegisterDTO;
import com.solution.picpay_problem.dtos.user.UserLoginDTO;
import com.solution.picpay_problem.security.authentication.TokenService;
import com.solution.picpay_problem.security.userDetails.UserDetailsIml;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public UserService (UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseEntity registerUser(UserRegisterDTO datas){
        // verifica se o usuario ja esta cadastrado sistema
         if (this.userRepository.findByCpfOrEmail(datas.cpf(), datas.email()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

         // constroi objeto User para mim
        UserEntity user = this.createObjUser(datas);

         this.userRepository.save(user);

         return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    public  ResponseEntity loginUser(UserLoginDTO datas){
        var usernamePasswrod = new UsernamePasswordAuthenticationToken(datas.email(),datas.senha());
        var auth = this.authenticationManager.authenticate(usernamePasswrod);

        var token = tokenService.createToken( (UserDetailsIml) auth.getPrincipal() );

        return ResponseEntity.ok(new TokenValueDTO(token));
    }

    private UserEntity createObjUser(UserRegisterDTO datas){
        String hashed_password = new BCryptPasswordEncoder().encode(datas.senha());
        UserEntity user = new UserEntity();
        user.setCpf(datas.cpf());
        user.setEmail(datas.email());
        user.setNome(datas.nome());
        user.setSenha(hashed_password);
        user.setCargo(datas.cargo());

        return user;
    }

}

