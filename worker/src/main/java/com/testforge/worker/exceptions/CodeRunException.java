package com.testforge.worker.exceptions;

public class CodeRunException extends RuntimeException{
    String message;

    public CodeRunException(){

    }

    public CodeRunException(String message){
        this.message=message;
    }
}

