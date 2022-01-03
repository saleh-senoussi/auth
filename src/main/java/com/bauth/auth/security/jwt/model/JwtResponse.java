package com.bauth.auth.security.jwt.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -1588409035179426000L;
	private final String token;
	private final String username;
	private final List<String> roles;
}
