package com.dtsvn.vbcwe.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollBackException extends RuntimeException {

    private String message;
    private static final long serialVersionUID = 1L;

    public RollBackException(String msg) {
        super(msg);
        this.message = msg;
    }

    public RollBackException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
