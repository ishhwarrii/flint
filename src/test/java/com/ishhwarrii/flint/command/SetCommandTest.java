package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetCommandTest {

    private final SetCommand command = new SetCommand();

    @Test
    public void execute_validArgs_storesValueAndReturnsOk() {
        Database db = new Database();

        String response = command.execute(db, List.of("name", "Ishwari"));

        assertEquals("OK", response);
        assertEquals("Ishwari", db.get("name"));
    }

    @Test
    public void execute_tooFewArgs_returnsErrorAndStoresNothing() {
        Database db = new Database();

        String response = command.execute(db, List.of("name"));

        assertEquals("ERR wrong number of arguments for 'SET' command", response);
        assertFalse(db.exists("name"));
    }

    @Test
    public void execute_tooManyArgs_returnsError() {
        Database db = new Database();

        String response = command.execute(db, List.of("name", "Ishwari", "extra"));

        assertEquals("ERR wrong number of arguments for 'SET' command", response);
    }
}
