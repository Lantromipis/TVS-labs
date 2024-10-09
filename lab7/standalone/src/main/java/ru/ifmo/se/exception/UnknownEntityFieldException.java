package ru.ifmo.se.exception;

import lombok.Getter;
import ru.ifmo.se.exception.fault.UnknownEntityFieldFault;

import javax.xml.ws.WebFault;
import java.io.Serial;

@Getter
@WebFault(name = "UnknownEntityField", faultBean = "ru.ifmo.se.exception.fault.UnknownEntityFieldFault")
public class UnknownEntityFieldException extends Exception {
    @Serial
    private static final long serialVersionUID = -923394585528818428L;
    private final UnknownEntityFieldFault faultInfo;

    public UnknownEntityFieldException(String entityName, String fieldName) {
        super("Unknown entity field with name '" + fieldName + "'");
        faultInfo = new UnknownEntityFieldFault(entityName, fieldName);
    }
}
