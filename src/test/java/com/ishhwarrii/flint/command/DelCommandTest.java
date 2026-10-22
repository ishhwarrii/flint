package com.ishhwarrii.flint.command;

import com.ishhwarrii.flint.storage.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DelCommandTest {

    private final DelCommand command = new DelCommand();

    @Test
    public void execute_existingKey_removesItAndReturnsOne() {
        Database db = new Database();
        db.set("name", "Ishwari");

        assertEquals("1", command.execute(db, List.of("name")));
        assertFalse(db.exists("name"));
    }

    @Test
    public void execute_missingKey_returnsZero() {
        Database db = new Database();

        assertEquals("0", command.execute(db, List.of("doesNotExist")));
    }

    @Test
    public void execute_noArgs_returnsError() {
        Database db = new Database();

        assertEquals("ERR wrong number of arguments for 'DEL' command",
                command.execute(db, List.of()));
    }
}
