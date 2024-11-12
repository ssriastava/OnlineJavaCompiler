package com.testforge.codecompile.exceptions;

public class FileNotCreatedException extends RuntimeException{
    String message;
    public FileNotCreatedException(){
    }

    public FileNotCreatedException(String message){
        this.message=message;
    }
}
