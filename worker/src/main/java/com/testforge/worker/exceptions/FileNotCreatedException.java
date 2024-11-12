package com.testforge.worker.exceptions;

public class FileNotCreatedException extends RuntimeException{
    String message;
    public FileNotCreatedException(){
    }

    public FileNotCreatedException(String message){
        this.message=message;
    }
}
