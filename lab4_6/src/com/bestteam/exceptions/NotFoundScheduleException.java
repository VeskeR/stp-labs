package com.bestteam.exceptions;

/**
 * Created by Andrew on 12/21/2016.
 */
public class NotFoundScheduleException extends Exception {
    public NotFoundScheduleException() {
        super("Have not found correct schedule to assign task to.");
    }
}
