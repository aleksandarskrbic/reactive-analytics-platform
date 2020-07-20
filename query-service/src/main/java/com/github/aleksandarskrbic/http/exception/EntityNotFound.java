package com.github.aleksandarskrbic.http.exception;

import java.io.Serializable;

public class EntityNotFound extends RuntimeException implements Serializable {

    public EntityNotFound(final String message) {
        super(message);
    }
}
