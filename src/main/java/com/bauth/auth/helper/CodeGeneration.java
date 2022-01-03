package com.bauth.auth.helper;

public class CodeGeneration {
    
    private static final int MIN = 100000;
    private static final int MAX = 999999;

    public static String generateCode() {
        return String.valueOf((int) ((Math.random() * (MAX - MIN) + MIN)));
      }
}
