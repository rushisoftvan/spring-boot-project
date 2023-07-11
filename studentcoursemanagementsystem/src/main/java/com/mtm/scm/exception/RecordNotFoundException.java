package com.mtm.scm.exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String msg){
       super(msg);
    }
}
