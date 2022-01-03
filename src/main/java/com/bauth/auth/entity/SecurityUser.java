package com.bauth.auth.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

    private String username;
    private String password;

    private Collection<GrantedAuthority> authorities;

    /*public SecurityUser(User user) {
        username = user.getUsername();
        password = user.getPassword();

        if(user.getRoles() != null) {
            authorities = user.getRoles().stream().map(role -> 
                            new SimpleGrantedAuthority(role.getRoleName().name())
                        )
                        .collect(Collectors.toList());
        } else {
            authorities = new HashSet<>();
        }
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
