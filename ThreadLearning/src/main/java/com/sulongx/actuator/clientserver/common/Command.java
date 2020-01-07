package com.sulongx.actuator.clientserver.common;

/**
 * @author Sulongx
 */
public abstract class Command {
    protected final String[] command;

    public Command(String[] command) {
        this.command = command;
    }

    public abstract String execute();
}
