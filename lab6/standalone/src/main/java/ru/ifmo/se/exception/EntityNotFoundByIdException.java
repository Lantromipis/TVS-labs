package ru.ifmo.se.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundByIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String entityId;

    public EntityNotFoundByIdException(String entityId) {
        super("There is no entity with id " + entityId);
        this.entityId = entityId;
    }
}
