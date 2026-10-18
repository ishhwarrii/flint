package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;

import java.util.List;

public interface Command {
    String execute(Database database, List<String> args);
}
