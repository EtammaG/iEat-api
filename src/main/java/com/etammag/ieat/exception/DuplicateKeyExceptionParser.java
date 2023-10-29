package com.etammag.ieat.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateKeyExceptionParser {
    public static String parse(DuplicateKeyException e) {
        String message = e.getMessage();
        int i = message.lastIndexOf(':') +2;
        return message.substring(i);
    }

}
