package com.lndc.sender.exceptions;

public class NotNaturalNumberException extends Throwable {
    private int code;

    public NotNaturalNumberException(int code) {
        this.code = code;
    }

    public NotNaturalNumberException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
