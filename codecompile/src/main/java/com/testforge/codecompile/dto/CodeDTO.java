package com.testforge.codecompile.dto;

public class CodeDTO {

    String code;
    String language;
    String token;


    public CodeDTO(String code, String language, String token) {
        this.code = code;
        this.language = language;
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
