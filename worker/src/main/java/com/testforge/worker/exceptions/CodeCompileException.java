package com.testforge.worker.exceptions;

public class CodeCompileException extends RuntimeException {
    String message;

    public CodeCompileException() {

    }

    public CodeCompileException(String message) {
        this.message = message;
    }
}