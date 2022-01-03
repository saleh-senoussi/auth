package com.bauth.auth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptsServiceImpl {
    
    @Value("${application.login.attempts.limit}")
    private int maxLoginAttempts;
/*
    private LoadingCache<String, Integer> attempts;

    public LoginAttemptsService() {
        super();
        attempts = CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.HOURS)
        .build( new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String s) {
                return 0;
            }
        });
    }

    @EventListener(AuthenticationSuccessEvent.class)
    public void loginSucceeded(AuthenticationSuccessEvent event) {
        attempts.invalidate(getIpFromEvent(event));
    }

    @EventListener(AuthenticationFailureBadCredentialsEvent.class)
    public void loginFailed(AuthenticationFailureBadCredentialsEvent event) {
        String ip = getIpFromEvent(event);
        int attemptsCount = 0;
        try {
            attemptsCount = attempts.get(ip);
            attemptsCount++;

            attempts.put(ip, attemptsCount);
        } catch (Exception e) {
            System.out.println("An error ooccured while checking for brute force.");
        }
    }

    public boolean isBlocked(String ip) {
        try {
            return attempts.get(ip) > maxLoginAttempts;
        } catch (ExecutionException e) {
            System.out.println("An error ooccured while checking for brute force.");
            return false;
        }
    }

    private String getIpFromEvent(AbstractAuthenticationEvent event) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        return auth.getRemoteAddress();
    }*/
}
