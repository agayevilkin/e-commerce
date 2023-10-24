package com.example.elcstore.exception;

import java.util.UUID;

public class TechnicalCharacteristicTitleInUseException extends InUseException {

    public static final String TECHNICAL_CHARACTERISTIC_TITLE_CANNOT_BE_DELETED = "TechnicalCharacteristicTitle with ID = %s cannot be deleted because it is used";

    public TechnicalCharacteristicTitleInUseException(UUID id) {
        super(String.format(TECHNICAL_CHARACTERISTIC_TITLE_CANNOT_BE_DELETED, id));
    }
}
