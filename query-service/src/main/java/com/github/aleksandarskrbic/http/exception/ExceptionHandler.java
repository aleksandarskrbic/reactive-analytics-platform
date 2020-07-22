package com.github.aleksandarskrbic.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<EntityNotFound> {

    @Override
    public Response toResponse(final EntityNotFound entityNotFound) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(entityNotFound.getMessage())
            .build();
    }
}
