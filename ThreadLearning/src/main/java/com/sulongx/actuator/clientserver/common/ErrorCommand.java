package com.sulongx.actuator.clientserver.common;

/**
 * @author Sulongx
 */
public class ErrorCommand extends Command {

    public ErrorCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        return null;
    }
}
