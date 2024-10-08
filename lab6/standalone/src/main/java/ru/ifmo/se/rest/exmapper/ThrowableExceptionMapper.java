package ru.ifmo.se.rest.exmapper;

import ru.ifmo.se.model.dto.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        exception.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(
                        ErrorDto
                                .builder()
                                .code(500)
                                .message("Internal server error")
                                .timestamp(OffsetDateTime.now().toString())
                                .build()
                )
                .build();
    }
}
