package com.solution.picpay_problem.enums.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum UserRole {
    COMUM("COMUM"),
    LOJISTA("LOJISTA");

    private final String role;
}
