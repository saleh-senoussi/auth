package com.bauth.auth.security.jwt.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.bauth.auth.entity.Role;
import com.bauth.auth.entity.User;
import com.bauth.auth.error.UserBadRequestException;
import com.bauth.auth.error.enums.UserAccountExceptionCode;
import com.bauth.auth.repository.UserRepository;
import com.bauth.auth.service.impl.LoginAttemptsServiceImpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final LoginAttemptsServiceImpl attemptsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * try {
		 * if(attemptsService.isBlocked(ip))
		 * } catch (Exception e) {
		 * //TODO: handle exception
		 * }
		 */
		Optional<User> user = Optional.ofNullable(this.userRepository.findByUsername(username));

		if (user.isPresent()) {
			return build(user.get());
		} else {
			throw new UserBadRequestException(UserAccountExceptionCode.InvalidCredentialsException);
		}
	}

	private List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> set) {
		return set.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()/* .name() */))
				.collect(Collectors.toList());
	}

	public UserDetails build(User user) {
		return new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return mapToGrantedAuthorities(user.getUserRoles());
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public boolean isAccountNonExpired() {
				return !user.isExpired();
			}

			@Override
			public boolean isAccountNonLocked() {
				return !user.isLocked();
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return !user.isPasswordExpired();
			}

			@Override
			public boolean isEnabled() {
				return user.isEnabled();
			}
		};
	}

}
