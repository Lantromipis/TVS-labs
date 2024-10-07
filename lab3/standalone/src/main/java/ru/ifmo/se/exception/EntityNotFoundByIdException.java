package ru.ifmo.se.exception;

import lombok.Getter;
import ru.ifmo.se.exception.fault.EntityNotFoundByIdFault;

import javax.xml.ws.WebFault;
import java.io.Serial;


@Getter
@WebFault(name = "EntityNotFoundById", faultBean = "ru.ifmo.se.exception.fault.EntityNotFoundByIdFault")
public class EntityNotFoundByIdException extends Exception {
    @Serial
    private static final long serialVersionUID = -923394585528818428L;
    private final EntityNotFoundByIdFault faultInfo;

    public EntityNotFoundByIdException(String entityName, String entityId) {
        super("There is no entity with id " + entityId);
        faultInfo = new EntityNotFoundByIdFault(entityName, entityId);
    }
}
