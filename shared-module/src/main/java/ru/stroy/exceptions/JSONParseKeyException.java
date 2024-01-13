package ru.stroy.exceptions;

import lombok.AllArgsConstructor;

public class JSONParseKeyException extends RuntimeException {
    public JSONParseKeyException(String message) {super(message);}
}
