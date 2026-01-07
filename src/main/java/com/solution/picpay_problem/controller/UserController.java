package com.solution.picpay_problem.controller;

import com.solution.picpay_problem.domain.user.UserService;
import com.solution.picpay_problem.dtos.user.UserDatas;
import com.solution.picpay_problem.dtos.user.UserEmailAndPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity userRegister (@RequestBody UserDatas datas){
        return userService.registerUser(datas);
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserEmailAndPassword datas){
        return userService.loginUser(datas);
    }
}
