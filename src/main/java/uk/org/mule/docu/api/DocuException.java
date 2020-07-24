package uk.org.mule.docu.api;

public class DocuException extends RuntimeException {
    public DocuException(String s, Throwable throwable) {
        super(s, throwable);
    }
}