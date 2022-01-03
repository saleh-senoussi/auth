package com.bauth.auth.error;

import com.bauth.auth.error.enums.BaseErrorEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class SingleErrorException {
    private final String message;
    private final BaseErrorEnum code;
}
