package com.alex.tasktable.dto;

public class ResponseDto {
    private Integer code;
    private String text;
    private String exception;

    public ResponseDto() {
    }

    public ResponseDto(Integer code, String text, String exception) {
        this.code = code;
        this.text = text;
        this.exception = exception;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return text;
    }

    public void setName(String text) {
        this.text = text;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
