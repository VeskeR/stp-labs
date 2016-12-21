package com.bestteam.exceptions;

/**
 * Created by Andrew on 12/21/2016.
 */
public class NotFoundTaskException extends Exception {
    public NotFoundTaskException() {
        super("Have not found task you specified.");
    }
}
