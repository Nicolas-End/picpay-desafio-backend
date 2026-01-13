package com.solution.picpay_problem.dtos.user;

import com.solution.picpay_problem.enums.user.UserRole;

public record UserRegisterDTO(String name, String cpf, String email, String password, UserRole role) {
}
