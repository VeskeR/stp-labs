package com.bestteam.exceptions;

/**
 * Created by Andrew on 12/21/2016.
 */
public class NotEnoughHoursException extends Exception {
    public NotEnoughHoursException() {
        super("Schedule does not have enough free hours to assign task to it.");
    }
}
