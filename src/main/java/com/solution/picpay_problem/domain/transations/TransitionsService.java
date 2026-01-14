package com.solution.picpay_problem.domain.transations;

import com.solution.picpay_problem.domain.user.UserEntity;
import com.solution.picpay_problem.domain.user.UserRepository;
import com.solution.picpay_problem.dtos.transitions.TransitionIdDTO;
import com.solution.picpay_problem.dtos.transitions.TransitionsDatasDTO;
import com.solution.picpay_problem.security.authentication.AuthenticatedUser;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransitionsService {

    private final AuthenticatedUser userInfos ;
    private final UserRepository userRepository;
    public TransitionsService(AuthenticatedUser userInfos, UserRepository userRepository){
        this.userInfos = userInfos;

        this.userRepository = userRepository;
    }


    @Transactional// diz que tudo sera desfeito se der errado
    public ResponseEntity requestNewTransition(TransitionsDatasDTO datas){

        UserEntity reciverUser = this.userRepository.findByCpfOrEmail(datas.receiverId(),datas.receiverId());
        UserEntity user = this.userInfos.get();


        if(reciverUser == null )return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (user.getBalance() < datas.value()) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        //reajustando saldo dos usuarios
        reciverUser.setBalance(reciverUser.getBalance() + datas.value());
        user.setBalance(user.getBalance() - datas.value());

        this.userRepository.save(reciverUser);
        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }


}
