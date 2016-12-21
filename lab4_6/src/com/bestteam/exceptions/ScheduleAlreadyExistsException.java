package com.bestteam.exceptions;

/**
 * Created by Andrew on 12/21/2016.
 */
public class ScheduleAlreadyExistsException extends Exception {
    public ScheduleAlreadyExistsException() {
        super("Another schedule with the same time already exists.");
    }
}
