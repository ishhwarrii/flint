package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import java.util.List;

public class DelCommand implements Command {

    @Override
    public String execute(Database database, List<String> args) {
        if (args.size() != 1) {
            return "ERR wrong number of arguments for 'DEL' command";
        }
        return database.delete(args.get(0)) ? "1" : "0";
    }
}
