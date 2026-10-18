package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import java.util.List;

public class SetCommand implements Command {

    @Override
    public String execute(Database database, List<String> args) {
        if (args.size() != 2) {
            return "ERR wrong number of arguments for 'SET' command";
        }
        database.set(args.get(0), args.get(1));
        return "OK";
    }
}
