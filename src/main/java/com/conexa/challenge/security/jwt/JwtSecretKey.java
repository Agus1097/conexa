package com.conexa.challenge.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtSecretKey {

    @Value("${secret.key}")
    private String secretKey;
}
