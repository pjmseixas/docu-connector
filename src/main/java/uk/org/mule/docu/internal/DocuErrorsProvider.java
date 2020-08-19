package uk.org.mule.docu.internal;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

public class DocuErrorsProvider implements ErrorTypeProvider {
    @Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        HashSet<ErrorTypeDefinition> errors = new HashSet<ErrorTypeDefinition>();
        errors.add(DocuErrorType.CONSENT_REQUIRED);
        errors.add(DocuErrorType.INVALID_REQUEST);
        return errors;
    }
}
