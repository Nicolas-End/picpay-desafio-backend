package com.solution.picpay_problem.domain.user;

import com.solution.picpay_problem.dtos.user.UserDatas;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity registerUser(UserDatas datas){
        // verifica se o usuario ja esta cadastrado sistema
         if (this.userRepository.findByCpfOrEmail(datas.cpf(), datas.email()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        String hashed_password = new BCryptPasswordEncoder().encode(datas.senha());
         UserEntity user = new UserEntity();
         user.setCpf(datas.cpf());
         user.setEmail(datas.email());
         user.setNome(datas.nome());
         user.setSenha(hashed_password);

         this.userRepository.save(user);

         return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
