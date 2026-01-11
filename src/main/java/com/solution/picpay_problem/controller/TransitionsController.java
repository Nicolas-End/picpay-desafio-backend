package com.solution.picpay_problem.controller;

import com.solution.picpay_problem.dtos.user.UserTransferEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransitionsController {

    @PostMapping("/transfer")
    public ResponseEntity userTransfer(@RequestBody UserTransferEmail datas){
        return  ResponseEntity.ok().build();
    }
}
