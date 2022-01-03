package com.bauth.auth.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {
    private static final long serialVersionUID = -3335297705139823370L;
	private String username;
	private String password;
}
