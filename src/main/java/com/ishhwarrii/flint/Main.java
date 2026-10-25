package com.ishhwarrii.flint;

import com.ishhwarrii.flint.command.CommandDispatcher;
import com.ishhwarrii.flint.command.RequestHandler;
import com.ishhwarrii.flint.server.FlintServer;
import com.ishhwarrii.flint.storage.Database;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Database database = new Database();
        CommandDispatcher dispatcher = new CommandDispatcher(database);
        RequestHandler handler = new RequestHandler(dispatcher);

        new FlintServer(6380, handler).start();
    }
}
