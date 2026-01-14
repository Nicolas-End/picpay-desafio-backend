package com.solution.picpay_problem.domain.user;

import com.solution.picpay_problem.enums.user.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TB_Usuarios")
public class UserEntity {


    @Id
    private String cpf;

    @Column
    private String name;

    @Column
    private double balance;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
