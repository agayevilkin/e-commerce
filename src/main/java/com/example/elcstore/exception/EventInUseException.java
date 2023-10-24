package com.example.elcstore.exception;

import java.util.UUID;

public class EventInUseException extends InUseException {

    public static final String EVENT_CANNOT_BE_DELETED = "Event with ID = %s cannot be deleted because it is used";

    public EventInUseException(UUID id) {
        super(String.format(EVENT_CANNOT_BE_DELETED, id));
    }
}
