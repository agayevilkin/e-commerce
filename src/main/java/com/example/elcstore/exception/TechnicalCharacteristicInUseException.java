package com.example.elcstore.exception;

import java.util.UUID;

public class TechnicalCharacteristicInUseException extends InUseException {
    public static final String TECHNICAL_CHARACTERISTIC_CANNOT_BE_DELETED = "TechnicalCharacteristic with ID = %s cannot be deleted because it is used";

    public TechnicalCharacteristicInUseException(UUID id) {
        super(String.format(TECHNICAL_CHARACTERISTIC_CANNOT_BE_DELETED, id));
    }
}
