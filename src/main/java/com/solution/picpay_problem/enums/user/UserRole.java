package com.solution.picpay_problem.enums.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum UserRole {
    Comum("Comum"),
    Lojista("Lojista");

    private final String role;
}
