package com.solution.picpay_problem.dtos.user;

import com.solution.picpay_problem.enums.user.UserRole;

public record UserDatas(String nome, int cpf, String email, double saldo, String senha, UserRole role) {
}
