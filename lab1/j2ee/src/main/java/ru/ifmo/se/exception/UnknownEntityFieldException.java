package ru.ifmo.se.exception;

public class UnknownEntityFieldException extends RuntimeException {
    public UnknownEntityFieldException(String fieldName) {
        super("Unknown entity field with name '" + fieldName + "'");
    }
}
