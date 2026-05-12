package com.e6.infrastructure.util.security;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class PasswordGenerator {

    private PasswordGenerator(){}

    private static final String CHARSET = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

    public static String generateTempPassword(){
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, 4)
                .mapToObj(i -> IntStream.range(0, 4)
                        .mapToObj(j -> String.valueOf(CHARSET.charAt(random.nextInt(CHARSET.length()))))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("-"));
    }
}
