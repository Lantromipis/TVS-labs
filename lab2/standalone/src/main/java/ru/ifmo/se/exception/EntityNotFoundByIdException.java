package ru.ifmo.se.exception;

public class EntityNotFoundByIdException extends RuntimeException {
    public EntityNotFoundByIdException(String entityId) {
        super("There is no entity with id " + entityId);
    }
}
