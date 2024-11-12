package com.testforge.codecompile.controller;

import com.testforge.codecompile.dto.CodeDTO;
import com.testforge.codecompile.dto.ResponseDTO;
import com.testforge.codecompile.processor.CodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCompilerController {

    @Autowired
    CodeProcessor processor;

    @PostMapping("/compileAndRun")
    public ResponseDTO getCompileAndRun(@RequestBody CodeDTO code){

        System.out.println("code received: "+code.getCode());
        return processor.processCode(code);
    }
}
