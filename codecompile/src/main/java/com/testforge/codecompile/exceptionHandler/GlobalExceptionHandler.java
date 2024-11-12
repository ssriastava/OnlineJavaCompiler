package com.testforge.codecompile.exceptionHandler;


import com.testforge.codecompile.dto.ErrorDTO;
import com.testforge.codecompile.exceptions.FileNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= FileNotCreatedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public @ResponseBody ErrorDTO handleFileException(){
        return new ErrorDTO("Internal Server Error Occurred");
    }

}
