package org.ankol.server.tools;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super();
    }
}
