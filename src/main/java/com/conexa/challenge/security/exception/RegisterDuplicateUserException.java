package com.conexa.challenge.security.exception;

public class RegisterDuplicateUserException extends RuntimeException {
    public RegisterDuplicateUserException(String username) {
        super(String.format("El usuario %s ya se encuentra registrado", username));
    }
}
