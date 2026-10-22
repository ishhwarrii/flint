package com.ishhwarrii.flint.command;

import java.util.List;

public class RequestHandler {

    private final CommandDispatcher dispatcher;

    public RequestHandler(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String handle(String line) {
        List<String> tokens;
        try {
            tokens = CommandParser.parse(line);
        } catch (IllegalArgumentException e) {
            return "ERR " + e.getMessage();
        }

        if (tokens.isEmpty()) {
            return "ERR empty command";
        }

        String commandName = tokens.get(0);
        List<String> args = tokens.subList(1, tokens.size());
        return dispatcher.dispatch(commandName, args);
    }
}
