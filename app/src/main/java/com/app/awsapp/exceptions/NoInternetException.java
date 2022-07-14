package com.app.awsapp.exceptions;

import java.io.IOException;

/**
 * @Author Divya
 * @Date 09-07-2022
 * <p>
 * Information
 **/
public class NoInternetException extends IOException {

    private final String message;
    /*
     * Required when we want to add a custom message when throwing the exception
     * as throw new NoInternetException(" Custom Unchecked Exception ");
     */

    public NoInternetException(String message){
        super(message);
        this.message = message;
    }
    public NoInternetException(String message,Throwable throwable) {
        // calling super invokes the constructors of all super classes
        // which helps to create the complete stacktrace.
        super(message, throwable);
        this.message = message;
    }
}
