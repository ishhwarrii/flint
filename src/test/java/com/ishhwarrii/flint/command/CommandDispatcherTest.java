package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandDispatcherTest {

    @Test
    public void dispatch_setThenGet_roundTripsThroughSharedDatabase() {
        CommandDispatcher dispatcher = new CommandDispatcher(new Database());

        assertEquals("OK", dispatcher.dispatch("SET", List.of("name", "Ishwari")));
        assertEquals("Ishwari", dispatcher.dispatch("GET", List.of("name")));
    }

    @Test
    public void dispatch_commandNameIsCaseInsensitive() {
        CommandDispatcher dispatcher = new CommandDispatcher(new Database());

        assertEquals("OK", dispatcher.dispatch("set", List.of("name", "Ishwari")));
        assertEquals("Ishwari", dispatcher.dispatch("Get", List.of("name")));
    }

    @Test
    public void dispatch_unknownCommand_returnsError() {
        CommandDispatcher dispatcher = new CommandDispatcher(new Database());

        assertEquals("ERR unknown command 'FLY'",
                dispatcher.dispatch("FLY", List.of()));
    }
}
