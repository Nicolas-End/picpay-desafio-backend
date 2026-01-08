package com.solution.picpay_problem.controller;

import com.solution.picpay_problem.domain.user.UserService;
import com.solution.picpay_problem.dtos.user.UserRegisterDTO;
import com.solution.picpay_problem.dtos.user.UserLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity userRegister (@RequestBody UserRegisterDTO datas){
        return userService.registerUser(datas);
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserLoginDTO datas){
        return userService.loginUser(datas);
    }
}
