package com.example.mybatis.exception;

public class MyException extends RuntimeException{

    public MyException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
