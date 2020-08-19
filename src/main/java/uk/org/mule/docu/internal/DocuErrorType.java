package uk.org.mule.docu.internal;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum DocuErrorType implements ErrorTypeDefinition<DocuErrorType> {
    CONSENT_REQUIRED,
    INVALID_REQUEST
}
