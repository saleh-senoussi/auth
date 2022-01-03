package com.bauth.auth.error;

import javax.print.attribute.standard.DateTimeAtProcessing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {
    private final String code;
    private final String message;
    private final DateTimeAtProcessing timestamp;
}
