package com.client.wsRasmooplus.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnums {
    ADMINISTRADOR(1L),
    PROFESSOR(2L),
    ALUNO(3L);

    private Long id;

    UserTypeEnums(Long id) {
        this.id = id;
    }


}
