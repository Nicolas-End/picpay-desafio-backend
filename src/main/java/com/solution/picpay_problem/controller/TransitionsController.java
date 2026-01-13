package com.solution.picpay_problem.controller;

import com.solution.picpay_problem.domain.transations.TransitionsService;
import com.solution.picpay_problem.dtos.transitions.TransitionsDatasDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransitionsController {

    private final TransitionsService transitionsService;
    public TransitionsController(TransitionsService transitionsService){

        this.transitionsService = transitionsService;
    }

    @PostMapping("/transfer")
    public ResponseEntity createNewRequestTransfer(@RequestBody TransitionsDatasDTO datas){
        return transitionsService.requestNewTransition(datas) ;
    }
}
