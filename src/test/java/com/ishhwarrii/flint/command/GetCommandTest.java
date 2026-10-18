package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetCommandTest {

    private final GetCommand command = new GetCommand();

    @Test
    public void execute_existingKey_returnsValue() {
        Database db = new Database();
        db.set("name", "Ishwari");

        assertEquals("Ishwari", command.execute(db, List.of("name")));
    }

    @Test
    public void execute_missingKey_returnsNull() {
        Database db = new Database();

        assertNull(command.execute(db, List.of("doesNotExist")));
    }

    @Test
    public void execute_noArgs_returnsError() {
        Database db = new Database();

        assertEquals("ERR wrong number of arguments for 'GET' command",
                command.execute(db, List.of()));
    }
}
