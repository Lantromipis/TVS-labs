package ru.ifmo.se.rest.exmapper;

import ru.ifmo.se.exception.UnsupportedRsqlOperatorException;
import ru.ifmo.se.model.dto.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;

@Provider
public class UnsupportedRsqlOperatorExceptionMapper implements ExceptionMapper<UnsupportedRsqlOperatorException> {
    @Override
    public Response toResponse(UnsupportedRsqlOperatorException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                        ErrorDto
                                .builder()
                                .code(400)
                                .message("Operator with symbol '" + exception.getSymbol() + "' is not supported")
                                .timestamp(OffsetDateTime.now().toString())
                                .build()
                )
                .build();
    }
}
