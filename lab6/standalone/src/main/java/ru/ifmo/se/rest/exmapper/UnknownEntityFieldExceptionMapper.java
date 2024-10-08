package ru.ifmo.se.rest.exmapper;

import ru.ifmo.se.exception.UnknownEntityFieldException;
import ru.ifmo.se.model.dto.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;

@Provider
public class UnknownEntityFieldExceptionMapper implements ExceptionMapper<UnknownEntityFieldException> {
    @Override
    public Response toResponse(UnknownEntityFieldException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                        ErrorDto
                                .builder()
                                .code(400)
                                .message("Entity has no field named " + exception.getField())
                                .timestamp(OffsetDateTime.now().toString())
                                .build()
                )
                .build();
    }
}
