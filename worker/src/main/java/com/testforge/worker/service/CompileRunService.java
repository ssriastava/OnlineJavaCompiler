package com.testforge.worker.service;

import com.testforge.worker.dto.CodeDTO;
import com.testforge.worker.exceptions.CodeCompileException;
import com.testforge.worker.exceptions.CodeRunException;
import com.testforge.worker.exceptions.FileNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CompileRunService {

    @Autowired
    WebSocketService webSocketService;

    public void processCode(CodeDTO code){
        String output="";
        String filename=writeFile(code);
        System.out.println(filename);
        String compileMessage=compileFile(filename);
        if(compileMessage.equals("Success")){
            output=runFile(filename);
            System.out.println("Code compiled and run successfully: "+output);
        } else{
            output=compileMessage;
        }

        webSocketService.sendOutputToClient(code.getToken(), output);
    }

    public String runFile(String folderName){
        try{
            ProcessBuilder builder=new ProcessBuilder("java", "-cp",folderName, "Main");
            System.out.println(builder.command().stream().reduce((seed, str)->seed+" "+str));
            builder.redirectErrorStream(true);
            Process process=builder.start();
            String output="";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output=output+"\n"+line;
                }
            }
            int exitCode=process.waitFor();
            System.out.println(output);
            return exitCode==0?"Code successfully Run \n"+output:"Error while running code: \n"+output;
        } catch (IOException | InterruptedException e){
            throw new CodeRunException("Exception while Compiling Code");
        }
    }

    public String compileFile(String folderName){
        try{
            ProcessBuilder builder=new ProcessBuilder("javac", folderName+"/Main.java");
            builder.redirectErrorStream(true);
            Process process=builder.start();
            String output="";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output=output+"\n"+line;
                }
            }
            int exitCode=process.waitFor();
            System.out.println(output);
            if(exitCode==0){
                return "Success";
            } else {
                return output;
            }

        } catch (IOException | InterruptedException e){
            throw new CodeCompileException("Exception while Compiling Code");
        }
    }

    public String writeFile(CodeDTO code){
        String dirName=Long.toString(System.currentTimeMillis());
        File codeDir=new File("/"+dirName);
        if(!codeDir.exists()){
            codeDir.mkdir();
        }
        Path path= Paths.get("/"+dirName+"/Main.java");
        String javaCode=code.getCode();
        try{
            Files.writeString(path, javaCode, StandardCharsets.UTF_8);
            return "/"+dirName;
        } catch (IOException e){
            throw new FileNotCreatedException("Failed to create file "+"/"+dirName+"/Main.java");
        }
    }

}
