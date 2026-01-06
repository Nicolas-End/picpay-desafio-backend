package com.solution.picpay_problem.controller;

import com.solution.picpay_problem.domain.user.UserService;
import com.solution.picpay_problem.dtos.user.UserDatas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class AcessController {

    private final UserService userService;

    public AcessController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity userRegister (@Validated @RequestBody UserDatas datas){
        return userService.registerUser(datas);
    }
}
