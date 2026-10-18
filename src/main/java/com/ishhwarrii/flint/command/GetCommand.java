package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import java.util.List;

public class GetCommand implements Command {

    @Override
    public String execute(Database database, List<String> args) {
        if (args.size() != 1) {
            return "ERR wrong number of arguments for 'GET' command";
        }
        return database.get(args.get(0));
    }
}
