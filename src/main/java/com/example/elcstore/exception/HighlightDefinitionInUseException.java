package com.example.elcstore.exception;

public class HighlightDefinitionInUseException extends InUseException {

    public static final String  HIGHLIGHT_DEFINITION_CANNOT_BE_DELETED = "'Highlight Definition' in use and cannot be deleted.";

    public HighlightDefinitionInUseException() {
        super(HIGHLIGHT_DEFINITION_CANNOT_BE_DELETED);
    }
}
