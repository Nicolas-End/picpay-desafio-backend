package com.solution.picpay_problem.dtos.user;

import com.solution.picpay_problem.enums.user.UserRole;

public record UserRegisterDTO(String nome, String cpf, String email, String senha, UserRole cargo) {
}
