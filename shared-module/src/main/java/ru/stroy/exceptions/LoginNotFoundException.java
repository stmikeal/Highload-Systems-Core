package ru.stroy.exceptions;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String message) {super(message);}
}
