package com.bestteam.exceptions;

/**
 * Created by Andrew on 12/21/2016.
 */
public class NotFoundWorkerException extends Exception {
    public NotFoundWorkerException() {
        super("Have not found specified worker.");
    }
}
