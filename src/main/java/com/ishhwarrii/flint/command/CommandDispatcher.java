package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CommandDispatcher {

    private final Database database;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandDispatcher(Database database) {
        this.database = database;
        register("SET", new SetCommand());
        register("GET", new GetCommand());
        register("DEL", new DelCommand());
        register("EXISTS", new ExistsCommand());
    }

    private void register(String name, Command command) {
        commands.put(name, command);
    }

    public String dispatch(String commandName, List<String> args) {
        Command command = commands.get(commandName.toUpperCase(Locale.ROOT));
        if (command == null) {
            return "ERR unknown command '" + commandName + "'";
        }
        return command.execute(database, args);
    }
}

