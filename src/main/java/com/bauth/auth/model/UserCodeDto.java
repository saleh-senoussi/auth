package com.bauth.auth.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCodeDto implements Serializable {
    private final String username;
    private final String code;
}
