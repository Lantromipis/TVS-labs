package ru.ifmo.se.rest.exmapper;

import ru.ifmo.se.exception.EntityNotFoundByIdException;
import ru.ifmo.se.model.dto.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.OffsetDateTime;

public class EntityNotFoundByIdExceptionMapper implements ExceptionMapper<EntityNotFoundByIdException> {
    @Override
    public Response toResponse(EntityNotFoundByIdException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(
                        ErrorDto
                                .builder()
                                .code(404)
                                .message("There is not entity with id " + exception.getEntityId())
                                .timestamp(OffsetDateTime.now().toString())
                                .build()
                )
                .build();
    }
}
