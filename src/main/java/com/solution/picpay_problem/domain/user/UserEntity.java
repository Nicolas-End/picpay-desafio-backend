package com.solution.picpay_problem.domain.user;

import com.solution.picpay_problem.enums.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TB_Usuarios")
public class UserEntity {
    @Id
    private UUID id;

    @Column
    private String nome;

    @Column
    private double saldo;

    @Column(unique = true)
    private int cpf;

    @Column(unique = true)
    private String email;

    @Column
    private String senha;

    @Column
    private UserRole role;
}
